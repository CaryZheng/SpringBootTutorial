package com.zzb.tutorial.zmalldemo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsOrder {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("order_number")
    private String orderNumber;

    @TableField("user_id")
    private Long userId;

    @TableField("goods_id")
    private Long goodsId;

    private Integer state;

    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
