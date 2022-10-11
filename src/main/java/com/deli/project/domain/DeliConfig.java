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
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/board/**","/member/all","/member/{id}","/boards").order(1).excludePathPatterns("/member/new");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/member/all","member/{id}/edit","/member/{id}").order(2).excludePathPatterns("/member/new");
//        registry.addInterceptor(new ParameterInterceptor()).addPathPatterns("/board/**").order(3);

    }




}
