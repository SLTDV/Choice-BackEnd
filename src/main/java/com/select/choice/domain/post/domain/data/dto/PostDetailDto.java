package com.select.choice.domain.post.domain.data.dto;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailDto {
    private String authorname;
    private List<CommentDetailDto> comment;
}