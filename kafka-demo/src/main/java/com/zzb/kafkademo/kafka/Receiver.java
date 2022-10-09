package com.zzb.kafkademo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    public static final String TOPIC = "myTest";

    private Logger log = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = { Receiver.TOPIC })
    public void receiveMessage(ConsumerRecord<String, String> record) {
        log.info("Receive Message, key = {}, value = {}", record.key(), record.value());
    }
}
