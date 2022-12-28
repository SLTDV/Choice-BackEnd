package com.select.choice.domain.auth.domain.util;

import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.response.TokenResponse;

public interface AuthConverter {
    TokenResponse toResponse(TokenDto dto);
    TokenDto toTokenDto(String accessToken, String refreshToken, Long expiredAt);
}
