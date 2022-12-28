package com.select.choice.domain.auth.domain.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class SignUpDto {
    private final String email;
    private final String password;
    private final String nickname;
}
