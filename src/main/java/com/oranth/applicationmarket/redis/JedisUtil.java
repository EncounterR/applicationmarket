package com.oranth.applicationmarket.redis;

import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//@PropertySource("classpath:redis.properties")
public class JedisUtil {
	@Value("${redis.hostName}")
    private static String hostName;
 
    @Value("${redis.password}")
    private static String password;
 
    @Value("${redis.port}")
    private static Integer port;
 
    @Value("${redis.maxIdle}")
    private static Integer maxIdle;
 
    @Value("${redis.timeout}")
    private static Integer timeout;
 
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
 
    @Value("${redis.maxWaitMillis}")
    private static Integer maxWaitMillis;
	
    @Value("${redis.testOnBorrow}")
    private static boolean testOnBorrow;
 
    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;
	private static JedisPool jedisPool;	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(300);
		config.setMaxWaitMillis(1000);
	    config.setTestOnBorrow(true);
	    config.setMaxTotal(1000);
	    jedisPool = new JedisPool(config,"47.107.112.230",6379,10000,"lstroot");
	}

	public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis) {
        jedis.close();
    }
}
