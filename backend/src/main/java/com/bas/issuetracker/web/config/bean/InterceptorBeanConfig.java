package com.bas.issuetracker.web.config.bean;

import com.bas.issuetracker.web.config.interceptor.AuthInterceptor;
import com.bas.issuetracker.web.service.oauth.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorBeanConfig {

    private final TokenService tokenService;

    public InterceptorBeanConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor(tokenService);
    }
}
