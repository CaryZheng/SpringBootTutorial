package com.zzb.tutorial.loggerdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home() {
        return "Demo: logger";
    }

    @PostMapping("/httptest")
    public String test(@RequestHeader("MySign") String mySign, @RequestBody String body) {
        log.info("mySign = " + mySign);
        log.info("body = " + body);

        String md5Value = DigestUtils.md5DigestAsHex(body.getBytes());
        log.info("md5Value = " + md5Value);

        if (mySign.equals(md5Value)) {
            return "ok";
        }

        return "error";
    }
}
