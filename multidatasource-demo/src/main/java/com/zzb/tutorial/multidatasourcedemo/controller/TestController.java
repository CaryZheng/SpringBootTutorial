package com.zzb.tutorial.multidatasourcedemo.controller;

import com.zzb.tutorial.multidatasourcedemo.entity.User;
import com.zzb.tutorial.multidatasourcedemo.entity2.Message;
import com.zzb.tutorial.multidatasourcedemo.repository.UserRepository;
import com.zzb.tutorial.multidatasourcedemo.repository2.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("jpa")
    public String test() {
        userRepository.save(new User("Tony", 18));

        messageRepository.save(new Message("Hello", "World"));

        return "Success";
    }
}
