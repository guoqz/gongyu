package com.guoqz.gongyu.config;

import com.guoqz.gongyu.interpector.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类，
 * 需要实现 WebMvcConfigurer接口
 * 实现 addInterceptors方法
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 指定要拦截那些内容（无法拦截 .jsp）
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/css/**", "/image/**", "/js/**", "/layui/**", "/picture/**", "favicon.ico", "/admin/login"
        );
    }
}
