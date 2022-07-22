package com.zzb.kafkademo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private Logger log = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    public void send(String msg) {
        final String key = "my_msg";

        this.template.send(Receiver.TOPIC, key, msg);
        log.info("send message, key: {}, dada: {}", key, msg);
    }
}
