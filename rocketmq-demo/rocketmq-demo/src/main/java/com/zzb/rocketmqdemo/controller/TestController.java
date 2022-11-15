package com.zzb.rocketmqdemo.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    public static String topic = "myTestTopic";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通同步消息
     * @return
     */
    @GetMapping("/send")
    public String send() {

        String content = "Hello world from xxx";
        rocketMQTemplate.syncSend(topic, content);

        log.info("TestController syncSend to topic: " + topic + " , content: " + content);

        return "Send success";
    }

}
