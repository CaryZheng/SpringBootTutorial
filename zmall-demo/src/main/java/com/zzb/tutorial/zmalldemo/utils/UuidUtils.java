package com.zzb.tutorial.zmalldemo.utils;

import java.util.UUID;

public class UuidUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
