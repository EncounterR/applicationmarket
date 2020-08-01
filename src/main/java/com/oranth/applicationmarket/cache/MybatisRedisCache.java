package com.oranth.applicationmarket.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

public class MybatisRedisCache implements Cache {

    Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    private static final String PREFIX = "MyBatisCache_";

    private String id;

    private static RedisTemplate redisTemplate;

    public static void setRedisTemplate(RedisTemplate redisTemplate){
        MybatisRedisCache.redisTemplate = redisTemplate;
    }

    public MybatisRedisCache(String id){
        logger.debug("id="+id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {

 //       RedisConnection rc = redisTemplate.getConnectionFactory().getConnection();
        if(key != null && value != null){
            redisTemplate.opsForValue().set(PREFIX + key,value);
        }else {
            logger.debug(" cache key : " + PREFIX + key);
            logger.debug(" cache value : " + PREFIX + value);
        }

 //       rc.close();
    }

    @Override
    public Object getObject(Object o) {
//        RedisConnection rc = redisTemplate.getConnectionFactory().getConnection();
 //
        logger.debug("get key : " + o);
        Object value = null;
        try {
            value = redisTemplate.opsForValue().get(PREFIX + o);
        }catch (Exception e){
            logger.debug("get key : " + e.getClass().getName());
        }
        return value;
    }

    @Override
    public Object removeObject(Object key) {
//        RedisConnection rc = redisTemplate.getConnectionFactory().getConnection();
//        JdkSerializationRedisSerializer jsrs = new JdkSerializationRedisSerializer();
          Object obj = redisTemplate.opsForValue().get(key);
            redisTemplate.delete(key);
   //     logger.debug("remove key " + key);
        return obj;
    }

    @Override
    public void clear() {
        long l = redisTemplate.delete(redisTemplate.keys(PREFIX+"*"));
        logger.debug("clear"+l);
    }

    @Override
    public int getSize() {
        int num = redisTemplate.keys(PREFIX+"*").size();
        return num;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
