package com.select.choice.domain.auth.domain.util.Impl;

import com.select.choice.domain.auth.domain.data.dto.SignInDto;
import com.select.choice.domain.auth.domain.data.dto.SignUpDto;
import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.request.SignInRequest;
import com.select.choice.domain.auth.domain.data.request.SignUpRequest;
import com.select.choice.domain.auth.domain.data.response.TokenResponse;
import com.select.choice.domain.auth.domain.util.AuthConverter;
import com.select.choice.domain.user.domain.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthConverterImpl implements AuthConverter {

    @Override
    public TokenResponse toResponse(TokenDto dto) {
        String dtoAccessToken = dto.getAccessToken();
        String dtoRefreshToken = dto.getRefreshToken();
        Long dtoExpiredAt = dto.getExpiredAt();

        return TokenResponse.builder()
                .accessToken(dtoAccessToken)
                .refreshToken(dtoRefreshToken)
                .expiredAt(dtoExpiredAt)
                .build();
    }

    @Transactional
    @Override
    public TokenDto toTokenDto(String accessToken, String refreshToken, Long expiredAt) {
        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }

    @Override
    public SignInDto toSignInDto(SignInRequest signInRequest) {
        String reqEmail = signInRequest.getEmail();
        String reqPassword = signInRequest.getPassword();

        return SignInDto.builder()
                .email(reqEmail)
                .password(reqPassword)
                .build();
    }

    @Override
    public SignUpDto toSignUpDto(SignUpRequest signUpRequest) {
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
        String dtoPassword = signUpDto.getPassword();

        return User.builder()
                .email(dtoEmail)
                .nickname(dtoNickname)
                .password(dtoPassword)
                .build();
    }
}
