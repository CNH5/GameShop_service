package com.example.game_shop.config;

import com.example.game_shop.interceptor.PermissionInterceptor;
import com.example.game_shop.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author sheng
 * @date 2021/11/30 12:37
 */
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;

    @Resource
    private PermissionInterceptor permissionInterceptor;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置图片的路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + SystemAPI.imagePath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 重复提交拦截

        // token拦截
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/image/**")
                .excludePathPatterns("/error");
        // 权限拦截
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/image/*")
                .excludePathPatterns("/error");
    }
}
