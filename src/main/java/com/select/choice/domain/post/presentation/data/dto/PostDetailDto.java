package com.select.choice.domain.post.presentation.data.dto;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostDetailDto {
    private String writer;
    private List<CommentDetailDto> comment;
}