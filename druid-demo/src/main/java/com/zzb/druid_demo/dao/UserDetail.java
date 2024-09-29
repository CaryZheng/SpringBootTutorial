package com.zzb.druid_demo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String phone;
    private String password;
    private Date birthday;
    private Integer height;
    private Integer gender;
    private Integer education;
    private String avatar;
    private String wechat;
}
