package com.zzb.rocketmqdemo.rocketmq;

import com.zzb.rocketmqdemo.controller.TestController;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "myTestGroup", topic = "myTestTopic")
public class RocketMqConsumer implements RocketMQListener<String> {

    private Logger log = LoggerFactory.getLogger(RocketMqConsumer.class);

    @Override
    public void onMessage(String s) {
        log.info("RocketMqConsumer onMessage: {}", s);
    }
}
