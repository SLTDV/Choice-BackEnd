package com.select.choice.domain.user.presentation.data.response;

import com.select.choice.domain.post.presentation.data.response.PostResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class GetMyPageResponse {
    private final String nickname;
    private final String image;
    private final List<PostResponse> postList;
}
