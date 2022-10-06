package com.deli.project.domain;

import com.deli.project.domain.interceptor.AdminInterceptor;
import com.deli.project.domain.interceptor.LoginInterceptor;
import com.deli.project.domain.validation.AnotherException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class DeliConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/board/write","/member/all","/member/{id}","/boards").order(1).excludePathPatterns("/member/new");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/member/all","member/{id}/edit","/member/{id}").order(2).excludePathPatterns("/member/new");
    }




}
