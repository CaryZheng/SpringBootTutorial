package com.zzb.kubernetesdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Demo: Kubernetes";
    }

    @GetMapping("/test")
    public String test() {
        return "Demo: Kubernetes, for test";
    }
}
