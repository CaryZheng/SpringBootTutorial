package com.zzb.tutorial.zmalldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzb.tutorial.zmalldemo.dao.Goods;
import org.apache.ibatis.annotations.Select;

public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("SELECT id, name, price, num FROM goods WHERE id = #{id} FOR UPDATE")
    Goods selectByIdForUpdate(long id);
}
