package com.select.choice.domain.auth.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
    private final Long expiredAt;
}
