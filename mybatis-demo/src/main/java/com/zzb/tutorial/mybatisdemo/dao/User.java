package com.zzb.tutorial.mybatisdemo.dao;

import com.zzb.tutorial.mybatisdemo.common.CreateTime;
import com.zzb.tutorial.mybatisdemo.common.UpdateTime;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String idcardId;

    @CreateTime
    private Date createTime;

    @UpdateTime
    private Date updateTime;
}
