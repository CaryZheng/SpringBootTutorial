package com.zzb.tutorial.zmalldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface GoodsOrderMapper extends BaseMapper<GoodsOrder> {

    @Insert("INSERT INTO goods_order(user_id, goods_id, state) VALUES(#{userId}, #{goodsId}, #{state})")
    void createOrder(long userId, long goodsId, int state);

    @Select("SELECT id, user_id, goods_id, state FROM goods_order WHERE id = #{id} FOR UPDATE")
    GoodsOrder selectByIdForUpdate(long id);
}
