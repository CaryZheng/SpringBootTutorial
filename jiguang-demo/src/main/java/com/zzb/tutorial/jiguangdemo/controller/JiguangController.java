package com.zzb.tutorial.jiguangdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzb.tutorial.jiguangdemo.utils.RSADecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("/jiguang")
public class JiguangController {

    private Logger log = LoggerFactory.getLogger(JiguangController.class);

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

    @GetMapping("/verifyPhone")
    public HttpEntity<String> verifyPhone() {
        String testLoginToken = "gyvrAupFAbUU91bJAfBWAfI6eTENvI48g-1qXs4xpO_Md8nYQilSlSO7nX17uSFNtIfuJcE9ma5YVBLPClou1VA-qk5ShwvVH1rZAkaOe6UFtt6Ht1IpfYTCC2mE9OPZNLkfq_psG1PLLxuGuK70at3qQpQD19xxz8yh1pojlU6Po5DIh-q-xdJW__3Q6d8GiJnN_Wt-P7DOSf4fmYuTZK8mbMPa_p5qxknDJ1lAlUDIWh4CA8nZRTh74VsUmPHm";

        final String authorizationValue = JIGUNGA_APP_KEY + ":" +JIGUANG_MASTER_SECRECT;

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Basic " + Base64.getEncoder().encodeToString(authorizationValue.getBytes()));
        headers.set("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginToken", testLoginToken);
//        jsonObject.put("exID", "1234566");
        String body = jsonObject.toJSONString();

        HttpEntity<String> result = restTemplate.exchange(JIGUANG_API_VERIFY_URL, HttpMethod.POST, new HttpEntity<>(body, headers), String.class
        );

        log.info("JiguangController verifyPhone result = " + result);

        return result;
    }

    @GetMapping("/parsePhone")
    public String parseEncryptStr() throws Exception {
        String content = "vkAF4J3J0lj/HK9qzWisRDvONWqP8HuiRvkgJBXi270coQK0IoCwr/572HnDUH2jm2rDshF8mHVrmC5mMjxrXndI2Mc4jezFm+CxOSXCd3tH3gwS9u0SaIdnGL/vIRUDYjsosu9/MM0SYlWjzAUDbKNreKKEe5GjhDvFD+t1/gk=";
        String result = RSADecrypt.decrypt(content, RSA_PRIVATE_SECRECT);

        log.info("parseEncryptStr result = " + result);

        return result;
    }
}
