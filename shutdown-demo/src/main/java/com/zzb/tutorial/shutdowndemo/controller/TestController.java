package com.zzb.tutorial.shutdowndemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("sleep")
    public String sleep(@RequestParam Integer timeout) {
        try{
            log.info("Begin sleep: " + timeout);
            TimeUnit.SECONDS.sleep(timeout);
            log.info("End sleep: " + timeout);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Sleep:" + timeout;
    }
}
