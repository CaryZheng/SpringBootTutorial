package com.zzb.tutorial.jiguangdemo.controller;

import com.zzb.tutorial.jiguangdemo.common.CodeEnum;
import com.zzb.tutorial.jiguangdemo.common.ResponseWrapper;
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
    public ResponseWrapper verifyPhone(@RequestBody JiguangVerifyData param) {
        String loginToken = param.getLoginToken();
        if (null == loginToken || loginToken.isEmpty()) {
            return ResponseWrapper.fail(CodeEnum.CODE_100001);
        }

        String phone = jiguangService.verifyPhone(loginToken);

        log.info("JiguangController verifyPhone result = " + phone);

        if (null == phone || phone.isEmpty()) {
            return ResponseWrapper.fail(CodeEnum.CODE_200001);
        }

        return ResponseWrapper.success(phone);
    }

    @GetMapping("/sendPhoneMsg")
    public ResponseWrapper sendPhoneMsg() {
        String phone = "18217208700";
        String code = "987654";

        jiguangService.sendPhoneMsg(phone, code);

        return ResponseWrapper.success();
    }

}
