package com.lilac.config;

import com.lilac.interceptor.AutoFillInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    /**
     * 注册 MyBatis 自动填充拦截器
     *
     * @return
     */
    @Bean
    public AutoFillInterceptor autoFillInterceptor() {
        return new AutoFillInterceptor();
    }
}