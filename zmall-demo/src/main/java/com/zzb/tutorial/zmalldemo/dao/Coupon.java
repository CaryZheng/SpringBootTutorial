package com.zzb.tutorial.zmalldemo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Coupon {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer discount;

    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
}
