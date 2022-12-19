package com.select.choice.global.security.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt.secret")
public class JwtProperties {
    private String key;
}
