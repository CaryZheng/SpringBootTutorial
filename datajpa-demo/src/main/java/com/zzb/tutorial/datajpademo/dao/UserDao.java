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

    @Modifying
    @Query("UPDATE User u SET u.userName = ?1 WHERE u.id = ?2")
    int updateUserInfo1(@Param("userName") String userName, @Param("id") Integer userId);

    @Modifying
    @Query(value = "UPDATE user SET user.user_name = ?1 WHERE user.id = ?2", nativeQuery = true)
    int updateUserInfo2(String userName, Integer userId);
}
