package com.lif.mybatis.main;

import com.lif.mybatis.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: lifan
 * @Date: 2019/11/2 17:08
 */
public class RedisTemplateMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "values1");
        redisTemplate.opsForHash().put("hash", "field", "hvalue");
    }
}
