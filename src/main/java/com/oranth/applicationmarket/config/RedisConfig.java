package com.oranth.applicationmarket.config;


import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.utils.serializer.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
	@Value("${redis.hostName}")
    private String hostName;
 
    @Value("${redis.password}")
    private String password;
 
    @Value("${redis.port}")
    private Integer port;
    
//    @Value("redis.cluster.port.num")   
//    private Integer num;
    	
    @Value("${redis.maxIdle}")
    private Integer maxIdle;
 
    @Value("${redis.timeout}")
    private Integer timeout;
 
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
 
    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;
 
    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
 
    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;
 
    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
 
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;
 
    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;
    
    @Value("${redis.minTotal}")
    private Integer minTotal;
    
    /**
     * 连接池 配置
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(minTotal);       
        //当池内没有可用的连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(minEvictableIdleTimeMillis);
        //------其他属性根据需要自行添加-------------
        return jedisPoolConfig;
    }
    /**
     * jedis连接工厂
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        //单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        //设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(hostName);
        //设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(0);
        //设置密码
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        //设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(port);
        
        //获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        //指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
        jpcb.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        //单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }
    
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

//        //key序列化方式
//        template.setKeySerializer(redisSerializer);
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        //value序列化
//        template.setValueSerializer(new JdkSerializationRedisSerializer());
//        //value hashmap序列化
//        template.setHashValueSerializer(redisSerializer);
//        //key haspmap序列化
//        template.setHashKeySerializer(redisSerializer);
          MybatisRedisCache.setRedisTemplate(template);
        return template;
    }
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(JedisPoolConfig jedisPoolConfig){
//    	RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//    	List<RedisNode> nodes = new ArrayList<RedisNode>();
//    	for(int i = port.intValue() + 1;i < num.intValue(); ++i){
//    		RedisNode redisNode =  new RedisNode(hostName,i);
//    		nodes.add(redisNode);
//    	}
//    	redisClusterConfiguration.setClusterNodes(nodes);
//    	
//    	
//    	
//    	return redisClusterConfiguration;
//    }
    
    
    
    /**
     * 非连接池模式
     * @return
     */
/*    @Bean
    public JedisConnectionFactory JedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        //由于我们使用了动态配置库,所以此处省略
        //redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
         //序列化 反序列化
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.build());
        
        return factory;
    }*/
}
