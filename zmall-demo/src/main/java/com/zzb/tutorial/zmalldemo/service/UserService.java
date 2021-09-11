package com.zzb.tutorial.zmalldemo.service;

import com.zzb.tutorial.zmalldemo.dao.User;

public interface UserService {
    User signup(String phone, String password);

    boolean isAccountExisted(String phone);

    User getAccountInfo(String phone, String password);

    User getAccountInfo(Long userId);
}
