package com.zzb.tutorial.zmalldemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzb.tutorial.zmalldemo.annotation.NoLogin;
import com.zzb.tutorial.zmalldemo.common.CodeEnum;
import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.User;
import com.zzb.tutorial.zmalldemo.data.UserRD;
import com.zzb.tutorial.zmalldemo.dto.UserDTO;
import com.zzb.tutorial.zmalldemo.service.UserService;
import com.zzb.tutorial.zmalldemo.utils.JwtTokenUtils;
import com.zzb.tutorial.zmalldemo.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @NoLogin
    @PostMapping("/signup")
    public ResponseWrapper signup(@RequestBody UserRD userRD) {
        String phone = userRD.getPhone();
        String password = userRD.getPassword();

        if (userService.isAccountExisted(phone)) {
            // 该账号已存在
            return ResponseWrapper.fail(CodeEnum.CODE_200001);
        }

        // 注册
        User newUser = userService.signup(phone, password);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(newUser, userDTO);

        return ResponseWrapper.success(userDTO);
    }

    @NoLogin
    @PostMapping("/signin")
    public ResponseWrapper signin(@RequestBody UserRD userRD) {
        String phone = userRD.getPhone();
        String password = userRD.getPassword();

        User userResult = userService.getAccountInfo(phone, password);
        if (null == userResult) {
            // 该账号不存在
            return ResponseWrapper.fail(CodeEnum.CODE_200002);
        }

        String token = JwtTokenUtils.generateToken(userResult.getId().toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        return ResponseWrapper.success(jsonObject);
    }

    @GetMapping("/info")
    public ResponseWrapper getUserInfo() {
        Long userId = UserThreadLocal.getUserId();

        User userResult = userService.getAccountInfo(userId);
        if (null == userResult) {
            // 该账号不存在
            return ResponseWrapper.fail(CodeEnum.CODE_200003);
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userResult, userDTO);

        return ResponseWrapper.success(userDTO);
    }

}
