package com.select.choice.domain.auth.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@RequiredArgsConstructor
public class SignUpDto {
    private final String email;
    private final String password;
    private final String nickname;
    private final Optional<String> profileImageUrl;
}
