package com.select.choice.infrastructure.feign.config;

import com.select.choice.infrastructure.feign.error.FeignClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableFeignClients(basePackages = "com.select.choice.infrastructure.feign")
@Import(FeignClientErrorDecoder.class)
@Configuration
public class FeignConfig {
    @Bean
    @ConditionalOnMissingBean(ErrorDecoder.class)
    public FeignClientErrorDecoder commonFeignErrorDecoder() {
        return new FeignClientErrorDecoder();
    }
}
