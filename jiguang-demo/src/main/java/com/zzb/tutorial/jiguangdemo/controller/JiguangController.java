package com.zzb.tutorial.jiguangdemo.controller;

import com.zzb.tutorial.jiguangdemo.data.JiguangVerifyData;
import com.zzb.tutorial.jiguangdemo.service.JiguangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jiguang")
public class JiguangController {

    private Logger log = LoggerFactory.getLogger(JiguangController.class);

    @Autowired
    private JiguangService jiguangService;

    @GetMapping("/verifyPhone")
    public String verifyPhone(@RequestBody JiguangVerifyData param) {
        String loginToken = param.getLoginToken();
        String phone = jiguangService.verifyPhone(loginToken);

        log.info("JiguangController verifyPhone result = " + phone);

        return phone;
    }

}
