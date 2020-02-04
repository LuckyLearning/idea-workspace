package com.lif.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务注册与发现
 *
 * @Author: lifan
 * @Date: 2020/1/31 17:02
 */
@SpringBootApplication
@EnableEurekaServer // Eureka Server服务器端启动类，接受其它微服务注册进来
public class Config_Git_EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Config_Git_EurekaServerApplication.class, args);
    }
}
