package com.deli.project.domain;

import com.deli.project.domain.interceptor.AdminInterceptor;
import com.deli.project.domain.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 인터셉터 등록
 * LoginInterceptor : 로그인 성공시 접근 가능한 view 설정.
 * AdminInterceptor : 로그인을 한 후 회원 등급이 admin 인 경우 접근 가능한 view 설정.
 * */
@Configuration
public class DeliConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/board/**","/member/**","/boards").order(1).excludePathPatterns("/member/new");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/member/all").order(2).excludePathPatterns("/member/new","/member/{id}","/member/{id}/edit");


    }




}
