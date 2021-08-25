package com.zzb.tutorial.asyncdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTestService {

    private Logger log = LoggerFactory.getLogger(AsyncTestService.class);

    @Async
    public void voidTask1() throws InterruptedException {
        log.info("voidTask1开始执行, threadName = " + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        log.info("voidTask1执行结束, 耗时：{}毫秒, threadName = " + Thread.currentThread().getName(),(end - start));
    }
}
