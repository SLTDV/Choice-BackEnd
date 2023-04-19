package com.select.choice.domain.auth.presentation.data.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessExpiredTime;
    private final LocalDateTime refreshExpiredTime;
}
