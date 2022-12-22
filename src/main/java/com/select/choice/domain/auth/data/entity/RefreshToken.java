package com.select.choice.domain.auth.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "RefreshToken")
public class RefreshToken {
    @Id
    private Long userId;
    private String refreshToken;
    private LocalDateTime timeToLive;
}
