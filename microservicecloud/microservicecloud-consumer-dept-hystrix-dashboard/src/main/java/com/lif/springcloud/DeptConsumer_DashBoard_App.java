package com.lif.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * hystrix图形监控
 * @Author: lifan
 * @Date: 2020/2/2 17:30
 */
@SpringBootApplication
@EnableHystrixDashboard // 开启图形化仪表盘监控
public class DeptConsumer_DashBoard_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
    }
}
