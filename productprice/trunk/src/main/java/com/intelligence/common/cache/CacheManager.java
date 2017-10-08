/**
 * 
 */
package com.intelligence.common.cache;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import com.intelligence.common.exception.PowerException;

/**
 * @author p-sunhao
 *
 */
public class CacheManager implements Serializable {

	/**
	 * 生成的序列号
	 */
	private static final long serialVersionUID = 3416856090993684527L;
	
	/**
	 * 缓存管理器
	 */  
    volatile private static CacheManager singletonManager = null;
	
	
	/**
	 * 默认构造函数
	 * 
	 * @throws IOException 
	 */
	private CacheManager() throws IOException {
		// 创建内存缓存
		MemoryCache.initCache();
	}
	
	/**
	 * 获取单例的缓存管理器
	 * 
	 * @return
	 * @throws IOException 
	 */
	public static CacheManager getManager() throws PowerException {
		try {
			// 初始化单例
			if (singletonManager == null) {
				synchronized (CacheManager.class) {
					if (singletonManager == null) {
						singletonManager = new CacheManager();						
					}
				}
		    }
	        return singletonManager;
		} catch (IOException e) {
			throw new PowerException("对象序列化IO异常", e);
		}
	}
	
	/**
	 * 设置缓存
	 * 
	 * @param key
	 * @param data
	 * @param time
	 * @throws IOException 
	 */
	public synchronized void setCache(String key, Object data, Long time) throws PowerException {
		try {
			// 刷新缓存
			flushCache();
			
			// 获取缓存大小
			int cacheSize = MemoryCache.getCacheSizeToSet(key, data);
			int timeSize = MemoryCache.getTimeCacheSizeToSet(key);
			int queSize = MemoryCache.getQueSetSizeToSet(key);
			
			// 初始化缓存标记
			int cacheLevFlag = MemoryCache.CACHE_NUM_ZERO;
			
			// 判断是插一级缓存还是二级缓存，或者是不插
			if (cacheSize <= MemoryCache.ONE_K_LIMIT) {
				cacheLevFlag = MemoryCache.CACHE_NUM_ONE;
			} else if (cacheSize > MemoryCache.ONE_K_LIMIT && cacheSize <= MemoryCache.TWO_M_LIMIT) {
				cacheLevFlag = MemoryCache.CACHE_NUM_TWO;
			}
			
			// 判断是否能够缓存
			if (!isAllocMemory(cacheSize + timeSize + queSize)) {
				// 不能缓存
				return;
			}
			
			// 分级缓存数据
			if (1 == cacheLevFlag) {
				// 一级
				MemoryCache.setOneLevelCache(key, data, cacheSize);
			} else if (2 == cacheLevFlag) {
				// 二级
				MemoryCache.setTwoLevelCache(key, data, cacheSize);
			} else {
				// 不能缓存
				return;
			}
			
			// 插入其他缓存信息
			MemoryCache.setTimeCache(key, time, cacheSize + timeSize + queSize, timeSize);
			MemoryCache.setQueSet(key, queSize);
		} catch (IOException e) {
			throw new PowerException("对象序列化IO异常", e);	
		}
	}
	
	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key) {
		// 判断是否超时，如果超时则清理键，否则，返回分类获取一级和二级缓存
		if (MemoryCache.getTwoLevelCache().containsKey(key + MemoryCache.START_TIME) && MemoryCache.getTimeCache().get(key + MemoryCache.START_TIME) + MemoryCache.getTimeCache().get(key + MemoryCache.EXIST_TIME) < System.currentTimeMillis()) {
			clearCache(key);
			return null;
		} else if (MemoryCache.getOneLevelCache().containsKey(key)) {
			// 获取一级缓存
			return MemoryCache.getOneLevelCache().get(key);
		} else if (MemoryCache.getTwoLevelCache().containsKey(key)) {
			// 获取二级缓存
			return MemoryCache.getTwoLevelCache().get(key);
		} else {
			return null;
		}
	}
	
	/**
	 * 清除缓存
	 * 
	 * @param key
	 */
	public synchronized void clearCache(String key) {
		// 获取需要清理的size
		Long clearSize = MemoryCache.getTimeCache().get(key + MemoryCache.SIZE_TIME);
		if (null == clearSize) {
			return;
		}
		
		// 如果二级缓存没有清理，清理的是一级缓存，则跳过
		if (MemoryCache.getTwoLevelCache().containsKey(key)) {
			MemoryCache.clearTwoLevelCache(key);
		} else if (MemoryCache.getOneLevelCache().containsKey(key)) {
			MemoryCache.clearOneLevelCache(key);
		}
		
		// 清理其他维护信息
		MemoryCache.clearTimeCache(key);
		MemoryCache.clearQueSet(key);
		
		// 重置缓存大小
		MemoryCache.narrowCacheSize(clearSize);
	}
	
	/**
	 * 刷新并清理缓存
	 */
	public synchronized void flushCache() {
		// 获取需要删除的键
		while (MemoryCache.getCacheSize() > (long) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * MemoryCache.RATIO_MAX)
				&& MemoryCache.getCacheSize() > (long) ((Runtime.getRuntime().freeMemory() * MemoryCache.RATIO_MAX))) {
			Iterator<String> setIt = MemoryCache.getQueSet().iterator();
			while (setIt.hasNext()) {
				String setNext = setIt.next();
				if (MemoryCache.getOneLevelCache().containsKey(setNext) && MemoryCache.CACHE_NUM_ZERO != MemoryCache.getTwoLevelCache().size()) {
					setNext = null;
					continue;
				} else {
					clearCache(setNext);
					setNext = null;
					break;
				}
			}
			setIt = null;
		}
	}
	
	/**
	 * 判断是否需要分配内存
	 * 
	 * @return
	 */
	public boolean isAllocMemory(int allSize) {
		// 初始化需要分配的内存
		Long needSize = (long) (allSize * MemoryCache.ALLOC_RATIO);
		
		// 获取分配级别并初始化可分配内存
		Long allowMemory = 0l;
		if (Runtime.getRuntime().freeMemory() <= MemoryCache.ALLOC_ONE_STAND) {
			allowMemory = (long) (Runtime.getRuntime().freeMemory() * MemoryCache.ALLOC_ONE_LEVEL) + MemoryCache.CACHE_NUM_ONE;
		} else if (Runtime.getRuntime().freeMemory() <= MemoryCache.ALLOC_TWO_STAND && Runtime.getRuntime().freeMemory() > MemoryCache.ALLOC_ONE_STAND) {
			allowMemory = (long) (Runtime.getRuntime().freeMemory() * MemoryCache.ALLOC_TWO_LEVEL) + MemoryCache.CACHE_NUM_ONE;
		} else if (Runtime.getRuntime().freeMemory() <= MemoryCache.ALLOC_THREE_LEVEL && Runtime.getRuntime().freeMemory() > MemoryCache.ALLOC_TWO_STAND) {
			allowMemory = (long) (Runtime.getRuntime().freeMemory() * MemoryCache.ALLOC_THREE_LEVEL) + MemoryCache.CACHE_NUM_ONE;
		} else if (Runtime.getRuntime().freeMemory() <= MemoryCache.ALLOC_FOUR_STAND && Runtime.getRuntime().freeMemory() > MemoryCache.ALLOC_THREE_LEVEL) {
			allowMemory = (long) (Runtime.getRuntime().freeMemory() * MemoryCache.ALLOC_FOUR_LEVEL) + MemoryCache.CACHE_NUM_ONE;
		} else if (Runtime.getRuntime().freeMemory() > MemoryCache.ALLOC_FOUR_STAND) {
			allowMemory = (long) (Runtime.getRuntime().freeMemory() * MemoryCache.ALLOC_FIVE_LEVEL) + MemoryCache.CACHE_NUM_ONE;
		}
		
		// 判断内存是否够用
		if (allowMemory > needSize) {
			return true;
		} else {
			return false;
		}
	}
	
//		MemoryCache.destroyCache();
	
}
