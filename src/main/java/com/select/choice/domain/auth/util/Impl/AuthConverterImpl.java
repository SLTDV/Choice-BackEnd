package com.select.choice.domain.auth.util.Impl;

import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.presentation.data.dto.SignInDto;
import com.select.choice.domain.auth.presentation.data.dto.SignUpDto;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;
import com.select.choice.domain.auth.presentation.data.request.SignInRequest;
import com.select.choice.domain.auth.presentation.data.request.SignUpRequest;
import com.select.choice.domain.auth.presentation.data.response.TokenResponse;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuthConverterImpl implements AuthConverter {
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse toResponse(TokenDto dto) {
        return TokenResponse.builder()
                .accessToken(dto.getAccessToken())
                .refreshToken(dto.getRefreshToken())
                .accessExpiredTime(dto.getAccessExpiredTime())
                .refreshExpiredTime(dto.getRefreshExpiredTime())
                .build();
    }

    @Transactional
    @Override
    public TokenDto toDto(String accessToken, String refreshToken, LocalDateTime accessExp, LocalDateTime refreshExp) {
        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredTime(accessExp)
                .refreshExpiredTime(refreshExp)
                .build();
    }

    @Override
    public SignInDto toDto(SignInRequest signInRequest) {
        String reqEmail = signInRequest.getEmail();
        String reqPassword = signInRequest.getPassword();

        return SignInDto.builder()
                .email(reqEmail)
                .password(reqPassword)
                .build();
    }

    @Override
    public SignUpDto toDto(SignUpRequest signUpRequest) {
        String reqEmail = signUpRequest.getEmail();
        String reqNickname = signUpRequest.getNickname();
        String reqPassword = signUpRequest.getPassword();

        return SignUpDto.builder()
                .email(reqEmail)
                .nickname(reqNickname)
                .password(reqPassword)
                .build();
    }

    @Override
    public User toEntity(SignUpDto signUpDto) {
        String dtoEmail = signUpDto.getEmail();
        String dtoNickname = signUpDto.getNickname();
        String dtoPassword = passwordEncoder.encode(signUpDto.getPassword());

        return User.builder()
                .email(dtoEmail)
                .nickname(dtoNickname)
                .password(dtoPassword)
                .build();
    }

    @Override
    public RefreshToken toEntity(Long userIdx, String refreshToken) {
        return RefreshToken.builder()
                .userId(userIdx)
                .refreshToken(refreshToken)
                .build();
    }
}
