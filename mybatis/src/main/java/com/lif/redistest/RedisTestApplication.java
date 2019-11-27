package com.lif.redistest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author live_
 */
@SpringBootApplication(scanBasePackages = "com.lif.redistest")
@MapperScan(basePackages = "com.lif.redistest", annotationClass = Repository.class)
@EnableCaching
public class RedisTestApplication {

    private final RedisConnectionFactory redisConnectionFactory;

    private final RedisTemplate<String, Object> redisTemplate;

//    @Bean(name = "redisCacheManager")
//    public RedisCacheManager initRedisCacheManager() {
//        // Redis加锁的写入器
//        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
//        // 启动Redis缓存的默认设置
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        // 设置JDK序列化器
//        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                new JdkSerializationRedisSerializer()));
//        // 禁用前缀
//        config = config.disableKeyPrefix();
//        // 设置10min超时
//        config = config.entryTtl(Duration.ofMillis(10));
//        // 创建Redis缓存管理器
//        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
//        return redisCacheManager;
//    }

    @Autowired
    public RedisTestApplication(RedisConnectionFactory redisConnectionFactory, RedisTemplate redisTemplate) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 自定义初始化
     * @param
     * @return: void
     * @createDate: 2019/11/24 18:10
     */
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    /**
     * 改变RedisTemplate对于键的序列化策略
     * @param
     * @return: void
     * @createDate: 2019/11/24 18:10
     */
    private void initRedisTemplate() {
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisTestApplication.class, args);
    }

}
