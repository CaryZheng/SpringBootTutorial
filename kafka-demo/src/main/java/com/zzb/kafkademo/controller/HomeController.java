package com.zzb.kafkademo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Demo: kafka";
    }

}
