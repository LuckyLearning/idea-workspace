package com.lif.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡算法配置类
 * @Author: lifan
 * @Date: 2020/2/2 10:25
 */
@Configuration
public class MySelfRule {

    /**
     * 修改默认的Ribbon算法
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RandomRule_LF();
    }
}
