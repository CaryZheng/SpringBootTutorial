package com.zzb.kafkademo.controller;

import com.zzb.kafkademo.kafka.Receiver;
import com.zzb.kafkademo.kafka.Sender;
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

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @GetMapping("/send")
    public String send() {
        sender.send();

        return "Send success";
    }

    @GetMapping("/receive")
    public String receive() {
        receiver.receive();

        return "Receive success";
    }
}
