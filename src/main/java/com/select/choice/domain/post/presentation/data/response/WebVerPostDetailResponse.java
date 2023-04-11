package com.select.choice.domain.post.presentation.data.response;

import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WebVerPostDetailResponse {
    private final String title;
    private final String content;
    private final String firstImageUrl;
    private final String secondImageUrl;
    private final String writer;
    private final String profileImageUrl;
    private final List<CommentDetailDto> comment;
}
