package com.zzb.tutorial.redisdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MessageSubscriber {

    private Logger log = LoggerFactory.getLogger(MessageSubscriber.class);

    public static String CHANNEL = "work";

    public MessageSubscriber(RedisTemplate redisTemplate) {
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        redisConnection.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                log.info("Receive message: " + message);
            }
        }, CHANNEL.getBytes(StandardCharsets.UTF_8));
    }
}
