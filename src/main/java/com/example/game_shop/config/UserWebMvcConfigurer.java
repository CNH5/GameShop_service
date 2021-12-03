package com.example.game_shop.config;

import com.example.game_shop.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author sheng
 * @date 2021/11/30 12:37
 */
@Configuration
public class UserWebMvcConfigurer implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/image/*")
                .excludePathPatterns("/error");
    }
}
