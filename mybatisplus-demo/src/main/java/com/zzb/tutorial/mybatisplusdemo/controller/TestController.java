package com.zzb.tutorial.mybatisplusdemo.controller;

import com.zzb.tutorial.mybatisplusdemo.dao.User;
import com.zzb.tutorial.mybatisplusdemo.data.UserRD;
import com.zzb.tutorial.mybatisplusdemo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis-plus")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test1")
    public User test1() {
        User user = userMapper.selectById(1);

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

}
