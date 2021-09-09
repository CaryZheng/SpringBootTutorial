package com.zzb.tutorial.zmalldemo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private BigDecimal price;
    private int num;
}
