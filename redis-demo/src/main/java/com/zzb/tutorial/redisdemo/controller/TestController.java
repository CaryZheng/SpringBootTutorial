package com.zzb.tutorial.redisdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/string")
    public String doString() {
        final String key = "k2";

        // delete
        stringRedisTemplate.delete(key);

        // set
        stringRedisTemplate.opsForValue().set(key, "张三");

        // get
        String value = stringRedisTemplate.opsForValue().get(key);

        return value;
    }

    @GetMapping("/list")
    public List<String> doList() {
        // 清空指定的 key
        stringRedisTemplate.delete("k_l1");

        // list 添加元素
        stringRedisTemplate.opsForList().rightPush("k_l1", "v_l1");
        stringRedisTemplate.opsForList().rightPush("k_l1", "v_l2");
        stringRedisTemplate.opsForList().rightPush("k_l1", "v_l3");

        // 获取 list 长度
        Long listSize = stringRedisTemplate.opsForList().size("k_l1");

        // pop
        String popValue = stringRedisTemplate.opsForList().leftPop("k_l1");

        // 根据 key 获取 list
        List<String> list = stringRedisTemplate.opsForList().range("k_l1", 0, -1);

        return list;
    }
}
