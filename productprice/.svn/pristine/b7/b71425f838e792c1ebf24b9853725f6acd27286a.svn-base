package com.intelligence.common.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RedisTools extends RedisAbstractCache<String, Object> {


	/**
	 * 批量设置
	 * 
	 * @param user
	 * @return
	 */
	public List<Object> setStrings(final List<CacheBean> patchList) {
		Assert.notEmpty(patchList);
		final RedisSerializer<String> serializer = getRedisSerializer();
		List<Object> result = redisTemplate.executePipelined(
				new RedisCallback<Boolean>() {
					public Boolean doInRedis(RedisConnection connection)
							throws DataAccessException {
						for (CacheBean cacheBean : patchList) {
							byte[] serKey = serializer.serialize(cacheBean
									.getKey());
							byte[] serVal = serializer.serialize(cacheBean
									.getValue());
							connection.set(serKey, serVal);
						}
						return null;
					}
				}, serializer);
		return result;
	}

	/**
	 * 设置
	 * 
	 * @param key
	 * @param value
	 * @param exp
	 * @return
	 */
	public boolean setString(final String key, final String value, final Integer exp) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				final RedisSerializer<String> serializer = getRedisSerializer();
				byte[] serKey = serializer.serialize(key);
				byte[] serVal = serializer.serialize(value);
				if (null == exp) {
					connection.set(serKey, serVal);
				} else {
					connection.setEx(serKey, exp, serVal);
				}
				return true;
			}
		}, false, true);
		return result;
	}

	/**
	 * 删除 
	 * 
	 * @param key
	 */
	public void delete(final String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		delete(list);
	}

	/**
	 * 删除多个
	 * 
	 * @param keys
	 */
	public void delete(final List<String> keys) {
		redisTemplate.delete(keys);
	}

	/**
	 * 通过key获取 <br>
	 * ------------------------------<br>
	 * 
	 * @param keyId
	 * @return
	 */
	public Object getString(final String key) {
		Object result = redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] redisKey = serializer.serialize(key);
				byte[] value = connection.get(redisKey);
				if (value == null) {
					return null;
				}
				String name = serializer.deserialize(value);
				return name;
			}
		});
		return result;
	}
}