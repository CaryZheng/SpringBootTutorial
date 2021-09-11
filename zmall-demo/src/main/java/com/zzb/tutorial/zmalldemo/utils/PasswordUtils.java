package com.zzb.tutorial.zmalldemo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public static String encodePassword(String originalPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(originalPassword);

        return encodePassword;
    }

    public static boolean isMatch(String originalPassword, String encodePassword) {
        return new BCryptPasswordEncoder(10).matches(originalPassword, encodePassword);
    }
}
