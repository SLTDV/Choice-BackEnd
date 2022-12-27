package com.select.choice.domain.user.domain.data.response;

import com.select.choice.domain.post.domain.data.response.PostResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class GetMyPageResponse {
    private final String username;
    private final List<PostResponse> postList;
}
