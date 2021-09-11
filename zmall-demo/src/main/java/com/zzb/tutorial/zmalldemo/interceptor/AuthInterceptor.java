package com.zzb.tutorial.zmalldemo.interceptor;

import com.zzb.tutorial.zmalldemo.annotation.NoLogin;
import com.zzb.tutorial.zmalldemo.exception.TokenException;
import com.zzb.tutorial.zmalldemo.utils.UserThreadLocal;
import com.zzb.tutorial.zmalldemo.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserThreadLocal.clear();

        NoLogin noLoginAnnotaion = null;

        if (handler instanceof HandlerMethod) {
            noLoginAnnotaion = ((HandlerMethod)handler).getMethodAnnotation(NoLogin.class);
        }

        if (null != noLoginAnnotaion) {
            // 不需要校验X-Token
            return true;
        }

        // 需要校验X-Token
        String token = request.getHeader("X-Token");
        if (null == token || token.isEmpty()) {
            // token无效
            throw new TokenException();
        }

        Claims claims = JwtTokenUtils.parseToken(token);
        String userIdStr = claims.getSubject();
        Long userId = Long.valueOf(userIdStr);

        UserThreadLocal.setUserId(userId);

        return true;
    }
}
