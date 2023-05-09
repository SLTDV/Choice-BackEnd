package com.select.choice.domain.user.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WebMyPageDto {
    private final String nickname;
    private final String profileImageUrl;
    private final List<WebMyPagePostDto> postList;
}
