package com.select.choice.domain.auth.util;

import com.select.choice.domain.auth.data.dto.SignInDto;
import com.select.choice.domain.auth.data.dto.SignUpDto;
import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.data.response.TokenResponse;
import com.select.choice.domain.user.data.entity.User;

public interface AuthConverter {
    TokenResponse toResponse(TokenDto dto);
    TokenDto toDto(String accessToken, String refreshToken, Long expiredAt);

    SignInDto toDto(SignInRequest signInRequest);

    SignUpDto toDto(SignUpRequest signUpRequest);

    User toEntity(SignUpDto signUpDto);
}
