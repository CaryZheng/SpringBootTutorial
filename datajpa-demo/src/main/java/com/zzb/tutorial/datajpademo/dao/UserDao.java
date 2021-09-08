package com.zzb.tutorial.datajpademo.dao;

import com.zzb.tutorial.datajpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findByUserName(String name);

    List<User> findByUserNameAndPassword(String userName, String password);

    @Query("SELECT u FROM User u WHERE u.userName = ?1 AND u.password = ?2")
    User findByCustomInfo2(String userName, String password);

    @Query(value = "SELECT * FROM user WHERE user_name = :userName AND password = :password", nativeQuery = true)
    User findByCustomInfo3(@Param("userName") String userName, @Param("password") String password);

    // 注：若不使用jpa提供的内置方法，执行以下方法将不会自动动更新 create_time 和 update_time 等基础字段
    @Modifying
    @Query("UPDATE User u SET u.userName = ?1 WHERE u.id = ?2")
    int updateUserInfo1(@Param("userName") String userName, @Param("id") Integer userId);

    // 注：若不使用jpa提供的内置方法，执行以下方法将不会自动动更新 create_time 和 update_time 等基础字段
    @Modifying
    @Query(value = "UPDATE user SET user.user_name = ?1 WHERE user.id = ?2", nativeQuery = true)
    int updateUserInfo2(String userName, Integer userId);
}
