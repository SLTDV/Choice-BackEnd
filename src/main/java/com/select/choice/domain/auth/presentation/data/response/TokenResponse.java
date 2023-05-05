package com.select.choice.domain.auth.presentation.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private final String refreshToken;
    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm:ss")
    private final LocalDateTime accessExpiredTime;
    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm:ss")
    private final LocalDateTime refreshExpiredTime;}
