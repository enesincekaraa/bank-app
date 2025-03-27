package com.enesincekara.transaction_service.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            var context = SecurityContextHolder.getContext();
            if (context.getAuthentication() != null && context.getAuthentication().getCredentials() != null) {
                String token = context.getAuthentication().getCredentials().toString();
                template.header("Authorization", "Bearer " + token);
            }
        };
    }
}