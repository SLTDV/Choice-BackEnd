package com.select.choice.domain.user.domain.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class NicknameDto {
    private final String nickname;
}
