package com.zzb.tutorial.mybatisdemo.config;

import com.zzb.tutorial.mybatisdemo.common.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfiguration {

    /**
     * 前置拦截器,主要功能是插入前设置create_time,更新时设置update_time
     */
    @Bean
    public CustomInterceptor idInterceptor() {
        return new CustomInterceptor();
    }
}
