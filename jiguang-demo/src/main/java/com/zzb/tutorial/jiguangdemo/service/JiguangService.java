package com.zzb.tutorial.jiguangdemo.service;

public interface JiguangService {

    /**
     * 极光一键登录
     * @param loginToken
     * @return 手机号
     */
    String verifyPhone(String loginToken);
}
