package com.lif.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: lifan
 * @Date: 2019/11/2 16:46
 */
@Configuration
public class RedisConfig {

    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 连接池配置信息
     * @param
     * @Return: redis.clients.jedis.JedisPoolConfig
     * @createDate: 2019/11/2 17:06
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大空闲数
        jedisPoolConfig.setMaxIdle(30);
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(50);
        // 最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(2000);
        return jedisPoolConfig;
    }

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if(this.redisConnectionFactory != null) {
            return this.redisConnectionFactory;
        }
        // 创建Redis工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        // 获取单机的Reids配置
        RedisSentinelConfiguration rsCfg = connectionFactory.getSentinelConfiguration();
        connectionFactory.setHostName("192.168.1.4");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("123456");
        this.redisConnectionFactory = connectionFactory;
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // RedisTemplate会自动初始化StringSerializer,所以这里直接获取
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 设置字符串序列转化器，这样Spring就会把Redis的key当字符串处理了
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);

        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
