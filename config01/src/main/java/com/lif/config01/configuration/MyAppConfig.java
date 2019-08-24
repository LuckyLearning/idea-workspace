package com.lif.config01.configuration;

import com.lif.config01.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * @author： lifan
 * @createDate： 2019/8/22 23:34
 */
@Configuration
public class MyAppConfig {

    /*
    将返回值添加到容器中，容器中默认的这个组件的id就是返回值
    */

    @Bean
    public HelloService helloService() {
        System.out.println("配置类HelloService加载");
        return new HelloService();
    }
}
