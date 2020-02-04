package com.lif.springcloud.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证获取github上的配置信息
 * @Author: lifan
 * @Date: 2020/2/3 17:34
 */
@RestController
public class ConfigClientRest {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    private String getConfig() {
        String str = "applicationName: " + applicationName
                + "\t eurekaServers" + eurekaServers
                + "\t port: " + port;
        System.out.println("*****str: " + str);
        return str;
    }
}
