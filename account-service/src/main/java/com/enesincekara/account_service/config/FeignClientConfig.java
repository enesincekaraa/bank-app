package com.enesincekara.account_service.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String token = (String) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getCredentials();

                if (token != null) {
                    template.header("Authorization", "Bearer " + token);
                }
            }
        };
    }
}
