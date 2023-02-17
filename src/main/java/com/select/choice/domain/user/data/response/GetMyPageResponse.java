package com.select.choice.domain.user.data.response;

import com.select.choice.domain.post.data.response.PostListResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class GetMyPageResponse {
    private final String nickname;
    private final List<PostListResponse> postList;
}
