package com.select.choice.domain.auth.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@RequiredArgsConstructor
public class SignInDto {
    private final String phoneNumber;
    private final String password;
    private final Optional<String> fcmToken;
}
