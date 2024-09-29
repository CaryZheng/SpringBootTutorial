package com.zzb.druid_demo.service;


import com.zzb.druid_demo.dao.User;

public interface UserService {

    User getAccountInfo(Long userId);

}
