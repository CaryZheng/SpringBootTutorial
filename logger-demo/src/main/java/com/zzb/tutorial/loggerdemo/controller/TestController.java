package com.zzb.tutorial.loggerdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    private static int sCount = 0;

    @GetMapping("/logger")
    public String logger() {
        ++sCount;

        log.info("Start to execute /test/logger function, sCount = " + sCount);

        return "logger success";
    }
}
