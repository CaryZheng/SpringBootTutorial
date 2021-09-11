package com.zzb.tutorial.zmalldemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzb.tutorial.zmalldemo.common.CodeEnum;
import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.User;
import com.zzb.tutorial.zmalldemo.data.UserRD;
import com.zzb.tutorial.zmalldemo.dto.UserDTO;
import com.zzb.tutorial.zmalldemo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseWrapper signup(@RequestBody UserRD userRD) {
        String phone = userRD.getPhone();
        String password = userRD.getPassword();

        User user = new User();
        user.setPhone(phone);
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getPhone, phone);

        User userResult = userMapper.selectOne(userQueryWrapper);
        if (null != userResult) {
            // 该账号已存在
            return ResponseWrapper.fail(CodeEnum.CODE_200001);
        }

        // 注册
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(password);
        userMapper.insert(newUser);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(newUser, userDTO);

        return ResponseWrapper.success(userDTO);
    }

}
