package com.zzb.tutorial.jiguangdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzb.tutorial.jiguangdemo.service.JiguangService;
import com.zzb.tutorial.jiguangdemo.utils.JGSmsUtil;
import com.zzb.tutorial.jiguangdemo.utils.RSADecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class JiguangServiceImpl implements JiguangService {

    private Logger log = LoggerFactory.getLogger(JiguangServiceImpl.class);

    private final static String JIGUANG_API_VERIFY_URL = "https://api.verification.jpush.cn/v1/web/loginTokenVerify";

    @Value("${jiguang.appKey}")
    private String JIGUNGA_APP_KEY;

    @Value("${jiguang.masterSecrect}")
    private String JIGUANG_MASTER_SECRECT;

    // RSA私钥
    @Value("${jiguang.rsaPrivateSecrect}")
    private String RSA_PRIVATE_SECRECT;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String verifyPhone(String loginToken) {
        final String authorizationValue = JIGUNGA_APP_KEY + ":" +JIGUANG_MASTER_SECRECT;

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Basic " + Base64.getEncoder().encodeToString(authorizationValue.getBytes()));
        headers.set("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginToken", loginToken);
        String body = jsonObject.toJSONString();

        HttpEntity<String> result = restTemplate.exchange(JIGUANG_API_VERIFY_URL, HttpMethod.POST, new HttpEntity<>(body, headers), String.class
        );

        String bodyResult = result.getBody();
        JSONObject resultJsonObject = JSONObject.parseObject(bodyResult);
        int code = resultJsonObject.getIntValue("code");

        String phone = "";

        if (8000 == code) {
            String encryptPhone = resultJsonObject.getString("phone");
            if (null != encryptPhone && !encryptPhone.isEmpty()) {
                try {
                    phone = RSADecrypt.decrypt(encryptPhone, RSA_PRIVATE_SECRECT);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                log.info("parse phone result = " + phone);
            }
        }

        return phone;
    }

    @Override
    public void sendPhoneMsg(String phone, String code) {
        JGSmsUtil.sendSMSCode(phone, code);
    }
}
