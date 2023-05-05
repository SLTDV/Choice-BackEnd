package com.select.choice.domain.user.presentation.data.response;

import com.select.choice.domain.user.presentation.data.dto.WebPostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WebMyPageResponse {
    private final String nickname;
    private final String image;
    private final List<WebPostDto> postList;
}
