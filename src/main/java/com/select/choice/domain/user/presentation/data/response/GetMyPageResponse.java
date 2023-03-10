package com.select.choice.domain.user.presentation.data.response;

import com.select.choice.domain.post.presentation.data.response.PostResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class GetMyPageResponse {
    private final String nickname;
    private final List<PostResponse> postList;
}
