package com.dji.sample.configuration.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalMVCConfigurer implements WebMvcConfigurer {

    @Value("${url.manage.prefix}")
    private String managePrefix;

    @Value("${url.manage.version}")
    private String manageVersion;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 移除所有拦截器，不进行任何鉴权
    }
}
