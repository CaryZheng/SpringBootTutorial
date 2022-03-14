package com.zzb.tutorial.signdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    @PostMapping("/sign")
    public String sign(@RequestHeader("MySign") String mySign,
//                       @RequestHeader("Version") String version,
//                       @RequestHeader("Timestamp") String timestamp,
//                       @RequestHeader("Nonce") String nonce,
//                       @RequestHeader("SignatureMethod") String signatureMethod,
                       @RequestBody String body) {
        log.info("mySign = " + mySign);
        log.info("body = " + body);

        String md5Value = DigestUtils.md5DigestAsHex(body.getBytes());
        log.info("md5Value = " + md5Value);

//        if (mySign.equals(md5Value)) {
//            return "ok";
//        }

        try {
            String secret = "mytest";// 加密使用的key
            String message = body;// 需要加密的字符串（本项目是 "{uuid}_{timestamp}" ）

            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256HMAC.init(secretKey);

            String hash = byteArrayToHexString(sha256HMAC.doFinal(message.getBytes()));
            log.info(hash);

            return hash;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
