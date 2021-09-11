package com.zzb.tutorial.zmalldemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtTokenUtils {

    // 秘钥
    private static String secret = "zxcvb123456";

    // 过期时间(秒)
    private static long expire = 7 * 24 * 60 * 60;

    /*
    生成token
     */
    public static String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /*
    解析token
     */
    public static Claims parseToken(String token) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }

        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    检查token是否过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
