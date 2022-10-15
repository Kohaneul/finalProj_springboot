package com.deli.project;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageCustomize implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400");
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class,"/error/400");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500");
        factory.addErrorPages(errorPage500,errorPageEx,errorPage400);
    }
}
