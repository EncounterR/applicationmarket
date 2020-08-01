package com.oranth.applicationmarket.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheManager implements CacheManager,Destroyable{

	private RedisTemplate<Object,Object> redisTemplate;
	
	public RedisCacheManager(RedisTemplate<Object,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

	
	@Override
	public <K, T> Cache<K, T> getCache(String name) throws CacheException {
		RedisCache<K,T> redisCache = new RedisCache<K,T>(name,redisTemplate);
		return redisCache;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
