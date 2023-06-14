package com.select.choice.global.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override //CORS 설정
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://www.choice-time.com", "https://choice-hazel.vercel.app", "https://choice-time.com")
                .allowedMethods("GET","POST","DELETE","PUT","OPTIONS","PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
