package com.deli.project.domain;

import com.deli.project.domain.interceptor.AdminInterceptor;
import com.deli.project.domain.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DeliConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/board/**","/member/**","/boards").order(1).excludePathPatterns("/member/new");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/member/all").order(2).excludePathPatterns("/member/new","/member/{id}","/member/{id}/edit");


    }




}
