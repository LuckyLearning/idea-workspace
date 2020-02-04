package com.lif.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 部门微服务启动类
 *
 * @Author: lifan
 * @Date: 2020/1/31 13:48
 */
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient // 服务发现
public class Config_Git_DeptProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Config_Git_DeptProviderApplication.class, args);
    }
}
