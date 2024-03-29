package com.select.choice.domain.auth.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "AuthCode",timeToLive = 60 * 3)
public class AuthCode {
    @Id
    @Indexed
    private String phoneNumber;

    @Indexed
    private String code;

    @Builder
    public AuthCode(String phoneNumber, String code){
        this.phoneNumber = phoneNumber;
        this.code = code;
    }
}
