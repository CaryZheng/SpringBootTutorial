package com.zzb.tutorial.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzb.tutorial.mybatisplusdemo.dao.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT COUNT(1) FROM user")
    int getUserCount();
}
