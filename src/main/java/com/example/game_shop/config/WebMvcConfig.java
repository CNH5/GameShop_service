package com.example.game_shop.config;

import com.example.game_shop.filter.RepeatedlyReadFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sheng
 * @date 2021/12/25 10:25
 */
@Configuration
public class WebMvcConfig {
    @Bean
    public FilterRegistrationBean<RepeatedlyReadFilter> repeatedlyReadFilter() {
        // 设置过滤器
        FilterRegistrationBean<RepeatedlyReadFilter> registration = new FilterRegistrationBean<>();
        RepeatedlyReadFilter repeatedlyReadFilter = new RepeatedlyReadFilter();
        registration.setFilter(repeatedlyReadFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
