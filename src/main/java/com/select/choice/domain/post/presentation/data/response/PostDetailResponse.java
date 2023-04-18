package com.select.choice.domain.post.presentation.data.response;

import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostDetailResponse {
    private final String writer;
    private final String image;
    private final Integer page;
    private final Integer size;
    private final List<CommentDetailDto> comment;
}
