package com.oranth.applicationmarket.redis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


public class RedisCache<K, T> implements Cache<K, T> {
	private Long sessionOutTime = 7200000L;
	private Long realmOutTime = 3600000L;
	
	private Long time;
	
	private String keyPrefix = "redis.shiro.cache.key:";
	/**
	 * key前缀
	 */
	private static final String REDIS_SHIRO_CACHE_KEY_PREFIX = "redis.shiro.cache:";
	/**
	 * cache name
	 */
	private String name;
	/**
	 * jedis 连接工厂
	 */
	private JedisConnectionFactory jedisConnectionFactory;
	
	/**
	 * 序列化工具
	 * 使用自定义序列化工具不只为什么出现问题
	 */
//	private RedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

	 /*class Serializer{
		public String serialize(String str){
			serializer1.serialize(str);
			return subString(serializer1.serialize(str));
		}
		private String subString(byte[] serialize) {
			//  Auto-generated method stub
			return null;
		}
		private String subString(String str){
			if(!TextUtils.isEmpty(str))
				str = str.split(" ")[3];
			return str;
		}
	}*/
	RedisTemplate<Object,Object> redisTemplate;
//	ValueOperations<Object, Object> redisTemplate;
//	RedisConnection redisTemplate = null;
	public RedisCache(String name, RedisTemplate<Object,Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.name = name;
		keyPrefix += name;
//		pipeline
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(K key) throws CacheException {
		Object result = null;	
		try {
//			byte b [] = redisTemplate.get(SerializeUtils.serialize(generateKey(key)));
//			result = SerializeUtils.deserialize(b);
			result = redisTemplate.opsForValue().get(generateKey(key));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (null != redisTemplate) {
				redisTemplate.getConnectionFactory().getConnection().close();
			}
		}
		return (T) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T put(K key, T value) throws CacheException {
		Object result = null;
		time = sessionOutTime;
		try {			
//			redisTemplate.set(SerializeUtils.serialize(generateKey(key)),SerializeUtils.serialize(value));
//			redisTemplate.lPush(SerializeUtils.serialize(keyPrefix),SerializeUtils.serialize(generateKey(key)));
//			redisTemplate.pExpire(SerializeUtils.serialize(keyPrefix), time);
//			redisTemplate.pExpire(SerializeUtils.serialize(generateKey(key)), time);
//			result = SerializeUtils.deserialize(redisTemplate.get(SerializeUtils.serialize(generateKey(key))));
			redisTemplate.opsForValue().set(generateKey(key),value);
			redisTemplate.expire(generateKey(key), time, TimeUnit.MILLISECONDS);
//			redisTemplate.opsForList().rightPush(keyPrefix,generateKey(key));
//			redisTemplate.expire(keyPrefix,time,TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != redisTemplate) {
				redisTemplate.getConnectionFactory().getConnection().close();
			}
		}
		return (T) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(K key) throws CacheException {
		T result = null;
		try {
			result = (T) redisTemplate.opsForValue().get(generateKey(key));

			redisTemplate.expire(generateKey(key), 0,TimeUnit.MILLISECONDS);

			redisTemplate.expire(keyPrefix, 0,TimeUnit.MILLISECONDS);
		} catch (Exception e) {
		} finally {
/*			if (null != redisTemplate) {
				redisTemplate.close();
			}*/
		}
		return result;
	}

	@Override
	public void clear() throws CacheException {
		try {
			Long length = redisTemplate.opsForList().size(keyPrefix);
			if (0 == length) {
				return;
			}

			List<Object> keyList = redisTemplate.opsForList().range(keyPrefix, 0, length - 1);
			for (Object key : keyList) {
				redisTemplate.expire(key, 0,TimeUnit.MICROSECONDS);
			}

			redisTemplate.expire(keyPrefix, 0,TimeUnit.MICROSECONDS);
			keyList.clear();
		} catch (Exception e) {
		} finally {
/*			if (null != redisTemplate) {
				redisTemplate.close();
			}*/
		}
	}

	@Override
	public int size() {
		int length = 0;
		try {
			length = Math.toIntExact(redisTemplate.opsForList().size(keyPrefix));
		} catch (Exception e) {
//			 LOGGER.error("shiro redis cache size exception.", e);
		} finally {
/*			if (null != redisTemplate) {
				redisTemplate.close();
			}*/
		}
		return length;
	}

	@Override
	public Set keys() {
		RedisConnection redisTemplate = null;
		Set resultSet = null;
		try {
//			redisTemplate = jedisConnectionFactory.getConnection();
//
//			Long length = redisTemplate.lLen(SerializeUtils.serialize(keyPrefix));
//			if (0 == length) {
//				return resultSet;
//			}
//
//			List<byte[]> keyList = redisTemplate.lRange(SerializeUtils.serialize(keyPrefix), 0, length - 1);
//			resultSet = keyList.stream().map(bytes -> SerializeUtils.deserialize(bytes)).collect(Collectors.toSet());
		} catch (Exception e) {
		} finally {
/*			if (null != redisTemplate) {
				redisTemplate.close();
			}*/
		}
		return resultSet;
	}

	@Override
	public Collection<T> values() {
		return null;
	}

	/**
	 * 重组key 区别其他使用环境的key
	 *
	 * @param key
	 * @return
	 */
	private String generateKey(K key) {
		return REDIS_SHIRO_CACHE_KEY_PREFIX + name + "_" + key;
	}
	
}
