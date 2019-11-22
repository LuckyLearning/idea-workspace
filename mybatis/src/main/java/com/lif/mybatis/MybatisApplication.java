package com.lif.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
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

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private MessageListener redisMsgListener;

    // 任务池
    private ThreadPoolTaskScheduler taskScheduler;

    /**
     * 创建任务池，运行线程等待处理Redis的消息
     * @param
     * @Return: org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
     * @createDate: 2019/11/12 18:51
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if(taskScheduler != null) {
            return  taskScheduler;
        }
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义Redis的监听器
     * @param
     * @Return: org.springframework.data.redis.listener.RedisMessageListenerContainer
     * @createDate: 2019/11/13 19:24
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        // Redis连接工厂
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        // 设置运行任务池
        redisMessageListenerContainer.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道，topic1
        ChannelTopic topic1 = new ChannelTopic("topic1");
        // 使用监听器监听Redis的消息
        redisMessageListenerContainer.addMessageListener(redisMsgListener, topic1);
        return  redisMessageListenerContainer;
    }

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
