package com.select.choice.domain.post.presentation.data.response;

import com.select.choice.domain.post.presentation.data.dto.TodayPostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class TodayPostListResponse {
    private final List<TodayPostResponse> todayPosts;
}
