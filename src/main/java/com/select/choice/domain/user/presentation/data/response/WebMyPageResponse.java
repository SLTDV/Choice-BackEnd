package com.select.choice.domain.user.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WebMyPageResponse {
    private final String nickname;
    private final String profileImageUrl;
    private final List<WebMyPagePostResponse> postList;
}
