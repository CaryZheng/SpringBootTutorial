package com.zzb.tutorial.asyncdemo.controller;

import com.zzb.tutorial.asyncdemo.service.AsyncTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {

    private Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncTestService asyncTestService;

    @GetMapping("/")
    public String testAsync() throws InterruptedException {
        log.info("testAsync: 111111, threadName = " + Thread.currentThread().getName());
        asyncTestService.voidTask1();
        log.info("testAsync: 222222, threadName = " + Thread.currentThread().getName());

        return "testAsync";
    }

}
