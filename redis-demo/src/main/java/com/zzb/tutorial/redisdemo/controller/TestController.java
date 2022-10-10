package com.zzb.tutorial.redisdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.Set;

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

    @GetMapping("hash")
    public String doHash() {
        // delete
        stringRedisTemplate.opsForHash().delete("my_hash", "my_hash_key1");

        // put
        stringRedisTemplate.opsForHash().put("my_hash", "my_hash_key1", "my_hash_value1");
        stringRedisTemplate.opsForHash().put("my_hash", "my_hash_key2", "my_hash_value2");

        // get
        String hashValue = (String) stringRedisTemplate.opsForHash().get("my_hash", "my_hash_key2");

        return hashValue;
    }

    @GetMapping("set")
    public String doSet() {

        final String key = "my_set";

        // delete
        stringRedisTemplate.delete(key);

        // add
        stringRedisTemplate.opsForSet().add(key, "set_value_1");
        stringRedisTemplate.opsForSet().add(key, "set_value_2");

        // size
        Long size = stringRedisTemplate.opsForSet().size(key);

        // remove
        stringRedisTemplate.opsForSet().remove(key, "set_value_1");

        // is member
        Boolean isExsited = stringRedisTemplate.opsForSet().isMember(key, "set_value_2");

        // pop
        String value = stringRedisTemplate.opsForSet().pop(key);

        return value;
    }

    @GetMapping("sortedSet")
    public String doSortedSet() {
        final String key = "my_zset_java";

        // delete
        stringRedisTemplate.delete(key);

        // add
        stringRedisTemplate.opsForZSet().add(key, "张三", 98);
        stringRedisTemplate.opsForZSet().add(key, "李四", 60);
        stringRedisTemplate.opsForZSet().add(key, "王五", 80);
        stringRedisTemplate.opsForZSet().add(key, "赵六", 44);

        // get scroe
        Double score = stringRedisTemplate.opsForZSet().score(key, "李四");

        // increase score
        stringRedisTemplate.opsForZSet().incrementScore(key, "李四", 30);

        Double newScore = stringRedisTemplate.opsForZSet().score(key, "李四");

        // get index
        Long index = stringRedisTemplate.opsForZSet().rank(key, "王五");

        // 按 score 从小到大的顺序进行排序
        Set<ZSetOperations.TypedTuple<String>> result = stringRedisTemplate.opsForZSet().rangeWithScores(key, 0, -1);

        // 按 score 从大到小的顺序进行排序，取Top3
        Set<ZSetOperations.TypedTuple<String>> rankTop3 = stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 2);

        return "ok";
    }
}
