package com.select.choice.domain.auth.util.Impl;

import com.select.choice.domain.auth.domain.entity.AuthCode;
import com.select.choice.domain.auth.domain.entity.Authentication;
import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.presentation.data.dto.*;
import com.select.choice.domain.auth.presentation.data.request.ChangePasswordRequest;
import com.select.choice.domain.auth.presentation.data.request.SendPhoneNumberRequest;
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
import java.util.Optional;

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
        String reqPhoneNumber = signInRequest.getPhoneNumber();
        String reqPassword = signInRequest.getPassword();
        Optional<String> reqDeviceToken = signInRequest.getDeviceToken();

        return SignInDto.builder()
                .phoneNumber(reqPhoneNumber)
                .password(reqPassword)
                .deviceToken(reqDeviceToken)
                .build();
    }

    @Override
    public SignUpDto toDto(SignUpRequest signUpRequest) {
        String reqNickname = signUpRequest.getNickname().stripTrailing();
        String reqPassword = signUpRequest.getPassword();
        Optional<String> reqImgUrl = signUpRequest.getProfileImgUrl();

        return SignUpDto.builder()
                .phoneNumber(signUpRequest.getPhoneNumber())
                .nickname(reqNickname)
                .password(reqPassword)
                .profileImgUrl(reqImgUrl)
                .build();
    }

    @Override
    public User toEntity(SignUpDto signUpDto, boolean isProfileImage) {
        String dtoNickname = signUpDto.getNickname();
        String dtoPassword = passwordEncoder.encode(signUpDto.getPassword());

        if(isProfileImage){
            return User.builder()
                    .phoneNumber(signUpDto.getPhoneNumber())
                    .nickname(dtoNickname)
                    .password(dtoPassword)
                    .profileImageUrl(signUpDto.getProfileImgUrl().get())
                    .build();
        } else {
            return User.builder()
                    .phoneNumber(signUpDto.getPhoneNumber())
                    .nickname(dtoNickname)
                    .password(dtoPassword)
                    .build();
        }
    }

    @Override
    public RefreshToken toEntity(Long userIdx, String refreshToken) {
        return RefreshToken.builder()
                .userId(userIdx)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public SendPhoneNumberDto toDto(SendPhoneNumberRequest sendPhoneNumberRequest) {
        return SendPhoneNumberDto.builder()
                .phoneNumber(sendPhoneNumberRequest.getPhoneNumber())
                .build();
    }

    @Override
    public AuthCode toEntity(String numStr, String phoneNumber) {
        return AuthCode.builder()
                .phoneNumber(phoneNumber)
                .code(numStr)
                .build();
    }

    @Override
    public Authentication toEntity(String phoneNumber) {
        return Authentication.builder()
                .phoneNumber(phoneNumber)
                .build();
    }

    @Override
    public ChangePasswordDto toDto(ChangePasswordRequest request) {
        return ChangePasswordDto.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }
}
