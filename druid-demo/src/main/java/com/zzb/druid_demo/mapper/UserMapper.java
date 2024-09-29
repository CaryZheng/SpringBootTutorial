package com.zzb.druid_demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.druid_demo.dao.User;
import com.zzb.druid_demo.dao.UserDetail;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "id", property = "userTags",
//                    many = @Many(select = "com.zzb.wqh.mapper.UserTagMapper.getUserTagDetail2"))
//    })
//    @Select("SELECT * FROM user WHERE id=#{userId}")
//    UserDetail getUserDetail(Long userId);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "userTags",
                    many = @Many(select = "com.zzb.wqh.mapper.UserTagMapper.getUserTagDetail2"))
    })
    @Select("SELECT * FROM user")
    Page<UserDetail> getUserDetailPage(Page<UserDetail> page);
}
