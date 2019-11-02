package com.lif.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

@MapperScan(
        basePackages = "com.lif.mybatis.dao",
        annotationClass = Repository.class)
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    /**
     * 注入事务管理器，它由spring boot自动生成
     * @param null
     * @Return:
     * @createDate: 2019/10/30 19:52
     */
    @Autowired
    PlatformTransactionManager transactionManager;

    /**
     * 使用后初始化方法，观察自动生成的事务管理器
     * @param
     * @Return: void
     * @createDate: 2019/10/30 19:53
     */
    @PostConstruct
    public void viewTransactionManager() {
        // 启动前加入断点观察
        System.out.println(transactionManager.getClass().getName());
    }

}
