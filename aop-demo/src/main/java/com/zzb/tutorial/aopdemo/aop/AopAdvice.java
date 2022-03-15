package com.zzb.tutorial.aopdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AopAdvice {

    private Logger logger = LoggerFactory.getLogger(AopAdvice.class);

    @Pointcut("execution (* com.zzb.tutorial.aopdemo.controller.*.*(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeAdvice() {
        logger.info("beforeAdvice...");
    }

    @After("test()")
    public void afterAdvice() {
        logger.info("afterAdvice...");
    }

    @Around("test()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info("aroundAdvice 1");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 输出 http 请求信息
        logger.info("请求地址: " + request.getRequestURL().toString());
        logger.info("Http Method: " + request.getMethod());

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();

            logger.info("aroundAdvice exception: " + e.getMessage());
        }

        logger.info("aroundAdvice 2");

        return result;
    }
}
