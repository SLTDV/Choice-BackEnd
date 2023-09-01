package com.select.choice.domain.auth.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SignInDto {
    private final String phoneNumber;
    private final String password;
    private final String fcmToken;
}
