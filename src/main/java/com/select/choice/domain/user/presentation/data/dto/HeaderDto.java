package com.select.choice.domain.user.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class HeaderDto {
    private final String nickname;
    private final String image;
}
