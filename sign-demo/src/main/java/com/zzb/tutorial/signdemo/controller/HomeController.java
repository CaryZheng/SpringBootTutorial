package com.zzb.tutorial.signdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home() {
        return "Demo: sign";
    }

}
