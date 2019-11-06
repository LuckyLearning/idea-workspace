package com.lif.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

/**
 * 启动工程
 * @author： lifan
 * @createDate： 2019/11/4 16:11
 */
@MapperScan(
        basePackages = "com.lif.mybatis.dao",
        annotationClass = Repository.class)
@SpringBootApplication
public class MybatisApplication {

    /**
     * 注入事务管理器，它由spring boot自动生成
     * @param null
     * @Return:
     * @createDate: 2019/10/30 19:52
     */
    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 定义自定义后初始化方法
     * @param
     * @Return: void
     * @createDate: 2019/11/4 11:33
     */
    @PostConstruct
    public void init() {
        viewTransactionManager();
        initRedisTemple();
    }

    /**
     * 使用后初始化方法，观察自动生成的事务管理器
     * @param
     * @Return: void
     * @createDate: 2019/10/30 19:53
     */
    public void viewTransactionManager() {
        // 启动前加入断点观察
        System.out.println("=========>:" + transactionManager.getClass().getName());
    }

    /**
     * 设置redisTemplate的序列化器
     * @param
     * @Return: void
     * @createDate: 2019/11/4 11:33
     */
    private void initRedisTemple() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
