package com.zzb.tutorial.mybatisdemo.mapper;

import com.zzb.tutorial.mybatisdemo.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT COUNT(1) FROM `user`")
    int getUserCount();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(Integer id);

    @Insert("INSERT INTO user(user_name, password) VALUES(#{userName}, #{password})")
    Integer createUser(String userName, String password);

    @Insert("INSERT INTO user(user_name, password, create_time, update_time) VALUES(#{userName}, #{password}, #{createTime}, #{updateTime})")
    Integer createUser2(User user);
}
