package com.select.choice.domain.auth.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SignupDuplicationCheckDto {
    private final String email;
}
