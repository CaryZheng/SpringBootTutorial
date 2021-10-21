package com.zzb.tutorial.mybatisplusdemo.controller;

import com.zzb.tutorial.mybatisplusdemo.dao.User;
import com.zzb.tutorial.mybatisplusdemo.data.UserRD;
import com.zzb.tutorial.mybatisplusdemo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mybatis-plus")
public class TestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/count")
    public int getUserCount() {
        int userCount = userMapper.getUserCount();

        return userCount;
    }

    @GetMapping("/test1")
    public User test1(@RequestParam Long userId) {
        User user = userMapper.selectById(userId);

        log.info("mybatis-plus test1 result = " + user);

        return user;
    }

    @GetMapping("/create")
    public String createUser(@RequestBody UserRD userRD) {
        String userName = userRD.getUserName();
        String password = userRD.getPassword();

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        Integer result = userMapper.insert(user);

        return "createUser result = " + result;
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody UserRD userRD) {
        User user = userMapper.selectById(userRD.getUserId());

        user.setUserName(userRD.getUserName());

        userMapper.updateById(user);

        return user;
    }

}
