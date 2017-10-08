package com.intelligence.common.cache;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.intelligence.common.check.TypeCheck;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.reflect.ReflectTools;

@Component
public class MemcacheTools {
	
	/**
	 * httpRequest
	 */
	@Resource
	private HttpServletRequest httpRequest;
	
	/**
	 * httpResponse
	 */
	@Resource
	private HttpServletResponse httpResponse;
	
	/**
	 * 反射工具集合
	 */
	@Resource
	private ReflectTools reflectTools;
	
	/**
	 * memcache客户端
	 */
	private static MemcachedClient memcachedClient = null;
	
	/**
	 * 注入memcache
	 * 
	 * @param key
	 * @param Data
	 * @param exp
	 * @param owner
	 * @param methodName
	 * @param args
	 * @param configMap reset expire cas
	 * @return
	 */
	public Object injectMemcache(String key, Object owner, String methodName, Object[] args, HashMap<String, Object> configMap) {
		Object memResult = null;
		Boolean setFlag = false;
		
		try {
			if(null == configMap.get("reset")) {
				memResult = this.getMemData(key);
			}
			
			Integer expire = 600;
			if (null == memResult) {
				memResult = reflectTools.invokeMethod(owner, methodName, args);
				setFlag = true;
				if (TypeCheck.checkInteger(configMap.get("expire"))) {
					expire = (Integer)configMap.get("expire");
				}
			}
			
			if (setFlag && null != configMap.get("cas")) {
				GetsResponse<Integer> result = this.getMemcachedClient().gets(key);
				if (null == result || !this.getMemcachedClient().cas(key, expire, memResult, result.getCas())) {
					this.setMemData(key, expire, memResult);
				}	
			} else if (setFlag) {
				this.setMemData(key, expire, memResult);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memResult;
	}
	
	/**
	 * 获取memcache数据
	 * 
	 * @param key
	 * @return
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public Object getMemData(String key) throws PowerException  {
		try {
			return this.getMemcachedClient().get(key);			
		} catch (TimeoutException e) {
			throw new PowerException("memcache连接超时异常", e);
		} catch (InterruptedException e) {
			throw new PowerException("memcache连接中断异常", e);
		} catch (MemcachedException e) {
			throw new PowerException("memcached异常", e);
		}
	}
	
	/**
	 * 设置memcache数据
	 * 
	 * @param key
	 * @param exp
	 * @param data
	 * @return
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public boolean setMemData(String key, Integer exp, Object data) throws PowerException {
		try {
			return this.getMemcachedClient().add(key, exp, data);
		} catch (TimeoutException e) {
			throw new PowerException("memcache连接超时异常", e);
		} catch (InterruptedException e) {
			throw new PowerException("memcache连接中断异常", e);
		} catch (MemcachedException e) {
			throw new PowerException("memcached异常", e);
		}
	}

	/**
	 * 获取memcache客户端
	 * 
	 * @return
	 */
	public MemcachedClient getMemcachedClient() {
		if (null == memcachedClient) {
			ServletContext servletContext = httpRequest.getSession().getServletContext();
	        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	        memcachedClient = (MemcachedClient) context.getBean("xmemcachedClient");
		}
		return memcachedClient;
	}
	
	/**
	 * 判断memcache是否链接
	 * 
	 * @return
	 */
	public boolean isConnected() {
		try {
			this.getMemcachedClient().get("test");
			return true;
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			return false;
		}	
	}

}
