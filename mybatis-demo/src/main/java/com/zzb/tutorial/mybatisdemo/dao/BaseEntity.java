package com.zzb.tutorial.mybatisdemo.dao;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Date createTime;
    private Date updateTime;
}
