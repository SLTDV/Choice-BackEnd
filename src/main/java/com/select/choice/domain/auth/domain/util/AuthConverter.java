package com.select.choice.domain.auth.domain.util;

import com.select.choice.domain.auth.domain.data.dto.SignInDto;
import com.select.choice.domain.auth.domain.data.dto.SignUpDto;
import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.request.SignInRequest;
import com.select.choice.domain.auth.domain.data.request.SignUpRequest;
import com.select.choice.domain.auth.domain.data.response.TokenResponse;
import com.select.choice.domain.user.domain.data.entity.User;

public interface AuthConverter {
    TokenResponse toResponse(TokenDto dto);
    TokenDto toTokenDto(String accessToken, String refreshToken, Long expiredAt);

    SignInDto toSignInDto(SignInRequest signInRequest);

    SignUpDto toSignUpDto(SignUpRequest signUpRequest);

    User toEntity(SignUpDto signUpDto);
}
