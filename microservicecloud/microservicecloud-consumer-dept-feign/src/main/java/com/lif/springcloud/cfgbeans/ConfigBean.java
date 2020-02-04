package com.lif.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lifan
 * @Date: 2020/1/31 14:37
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced // 启用负载均衡，默认轮询，指定算法后就按指定的算法来选择
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 用指定的算法替代默认的轮询算法
     * @return
     */
    @Bean
    public IRule myRule() {
//        return new RandomRule();
        return new RoundRobinRule();
    }
}
