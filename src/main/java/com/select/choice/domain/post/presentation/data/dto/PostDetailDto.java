package com.select.choice.domain.post.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostDetailDto {
    private final String writer;
    private final String image;
    private final Pageable pageable;
    private final Boolean isMine;
    private final List<CommentDetailDto> commentList;
}