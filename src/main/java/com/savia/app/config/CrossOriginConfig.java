package com.savia.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CrossOriginConfig {
    @Value("${allowed.origin}")
    private String allowedOrigin;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                 .allowedOrigins(allowedOrigin)
                 .allowedMethods("GET", "POST", "PUT", "DELETE")
                 .maxAge(3600)
                 .allowedHeaders("*");
            }
        };
    }
}
