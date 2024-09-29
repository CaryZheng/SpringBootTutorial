package com.zzb.druid_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzb.druid_demo.dao.User;
import com.zzb.druid_demo.mapper.UserMapper;
import com.zzb.druid_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
