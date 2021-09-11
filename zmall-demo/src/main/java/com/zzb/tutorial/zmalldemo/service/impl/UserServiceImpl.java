package com.zzb.tutorial.zmalldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzb.tutorial.zmalldemo.common.CodeEnum;
import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.User;
import com.zzb.tutorial.zmalldemo.mapper.UserMapper;
import com.zzb.tutorial.zmalldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User signup(String phone, String password) {
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(password);
        userMapper.insert(newUser);

        return newUser;
    }

    @Override
    public boolean isAccountExisted(String phone) {
        User user = new User();
        user.setPhone(phone);
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getPhone, phone);

        User userResult = userMapper.selectOne(userQueryWrapper);
        if (null != userResult) {
            // 该账号已存在
            return true;
        }

        // 该账号不存在
        return false;
    }

    @Override
    public User getAccountInfo(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getPhone, phone);
        userQueryWrapper.eq(User::getPassword, password);

        User userResult = userMapper.selectOne(userQueryWrapper);
        return userResult;
    }

    @Override
    public User getAccountInfo(Long userId) {
        User user = new User();
        user.setId(userId);
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getId, userId);

        User userResult = userMapper.selectOne(userQueryWrapper);
        return userResult;
    }
}
