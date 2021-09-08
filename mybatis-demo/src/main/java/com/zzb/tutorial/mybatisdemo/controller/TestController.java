package com.zzb.tutorial.mybatisdemo.controller;

import com.zzb.tutorial.mybatisdemo.dao.User;
import com.zzb.tutorial.mybatisdemo.data.UserRD;
import com.zzb.tutorial.mybatisdemo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public String test() {
        int userCount = userMapper.getUserCount();

        return "userCount = " + userCount;
    }

    @GetMapping("/test1")
    public User test1() {
        User user = userMapper.getUser(1);

        return user;
    }

    @GetMapping("/create")
    public String createUser(@RequestBody UserRD userRD) {
        String userName = userRD.getUserName();
        String password = userRD.getPassword();

        User user = new User();
        user.setUserName(userRD.getUserName());
        user.setPassword(userRD.getPassword());

//        Integer result = userMapper.createUser(userName, password);
        Integer result = userMapper.createUser2(user);

        return "createUser result = " + result;
    }

}
