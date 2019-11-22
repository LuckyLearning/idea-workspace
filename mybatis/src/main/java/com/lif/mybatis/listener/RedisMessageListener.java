package com.lif.mybatis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author: lifan
 * @Date: 2019/11/10 10:39
 */
@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 消息体
        String body = new String(message.getBody());
        //渠道名称
        String topic  = new String(pattern);
        System.out.println(body);
        System.out.println(topic);
    }
}
