package com.zzb.druid_demo.controller;

import com.zzb.druid_demo.common.CodeEnum;
import com.zzb.druid_demo.common.ResponseWrapper;
import com.zzb.druid_demo.dao.User;
import com.zzb.druid_demo.dto.UserDTO;
import com.zzb.druid_demo.mapper.UserMapper;
import com.zzb.druid_demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/info")
    public ResponseWrapper getUserInfo() {
        Long userId = 1L;

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
