package com.zzb.tutorial.jiguangdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzb.tutorial.jiguangdemo.service.JiguangService;
import com.zzb.tutorial.jiguangdemo.utils.RSADecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final String JIGUNGA_APP_KEY = "f3ac088596401d5e0e77b007";
    private final String JIGUANG_MASTER_SECRECT = "6e370fefb2eaee10ab01ab4d";

    // RSA私钥
    private final String RSA_PRIVATE_SECRECT =
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO1iK3hmbzZGs7to" +
            "ZJIFhgd4yMZfFn670rIETnYPx7PW/acC2XoV7dT1dZoBjs84PHnJIBCbGUKt3vGk" +
            "d7znDf0tdpVQw36lpk57JdiiHN3cMpuWYqa9ANlOfwXomIWHCu1PHYlfCUnXf1KJ" +
            "wD6o8P7N5Fpo+tuRUMCn2wMqMFB1AgMBAAECgYAOxlBsUvvTBf2DyZCQ4hUge+0L" +
            "I7nzs17+w3Pat0q3r8bj0TKV/a1q1sg2CKjJzvc8oT8EqZKd6tJ4ecMWF89v9uVP" +
            "Eyf29Z+4nYqDDJPXz2M7E7wUDcdBHUs2HAdYYVlJlZHWIJqySj7ZQ9YHHxdYtlUs" +
            "VnxsVRGBgcC1rMps4QJBAPydMZzbVWp8KlsNbL4LSO9jkFZCpRXvFW3XQo+JsA0J" +
            "o9F5sTZwFLRBsqD16FbcxTlPfafiiiUia+ZbsTg3FvkCQQDwkLb07Jwbze5SvDBz" +
            "e691T2w3tJLjrTrGNnSBcnjMbi5C/s2mYavSRV/559ZYMz2uEca/Khc2d9mqk4CL" +
            "erhdAkEA59ClD6mjd8Wb5NSr/rZCZxQ6Bz6/WiJ9yr+oYCpdOYmoIHoJKsit50EG" +
            "2HjxjoyoHFCbIi5c0XkGBW4NcmXzmQJBALpml61OTWPp/DmVYkGJXBoc8XB0dcD+" +
            "8GS3uyy2Y02cLjJxNy4tzjI+2TBN6LMs+SUQ1xw25AerdDiM1HS/IW0CQGkD0XDB" +
            "Qp5ApEW1JcEu9nWHaJYLQfqr9Lvw1ML6+2qFaeyLzJ3Cn13SQM4EFkUfV9XLo4TO" +
            "65reauuuniNaxd4=";

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
}
