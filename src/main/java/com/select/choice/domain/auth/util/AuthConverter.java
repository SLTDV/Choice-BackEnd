package com.select.choice.domain.auth.util;

import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.presentation.data.dto.*;
import com.select.choice.domain.auth.presentation.data.request.SendPhoneNumberRequest;
import com.select.choice.domain.auth.presentation.data.request.SignInRequest;
import com.select.choice.domain.auth.presentation.data.request.SignUpRequest;
import com.select.choice.domain.auth.presentation.data.request.SignupDuplicationCheckRequest;
import com.select.choice.domain.auth.presentation.data.response.TokenResponse;
import com.select.choice.domain.user.domain.entity.User;

import java.time.LocalDateTime;

public interface AuthConverter {
    TokenResponse toResponse(TokenDto dto);
    TokenDto toDto(String accessToken, String refreshToken, LocalDateTime accessExp, LocalDateTime refreshExp);
    SignInDto toDto(SignInRequest signInRequest);
    SignUpDto toDto(SignUpRequest signUpRequest);
    User toEntity(SignUpDto signUpDto);
    RefreshToken toEntity(Long userIdx, String refreshToken);
    SendPhoneNumberDto toDto(SendPhoneNumberRequest sendPhoneNumberRequest);
    SignupDuplicationCheckDto toDto(SignupDuplicationCheckRequest signupDuplicationCheckRequest);
}
