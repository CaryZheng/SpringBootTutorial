package com.zzb.tutorial.zmalldemo.utils;

import java.util.HashMap;
import java.util.Map;

public class UserThreadLocal {

    private static ThreadLocal<Map<String, Object>> sThreadLocal = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        sThreadLocal.set(map);
    }

    public static Long getUserId() {
        Map<String, Object> map = sThreadLocal.get();
        if (null == map) {
            return null;
        }

        Long userId = (Long) map.get("userId");

        return userId;
    }

    public static void clear() {
        sThreadLocal.remove();
    }
}
