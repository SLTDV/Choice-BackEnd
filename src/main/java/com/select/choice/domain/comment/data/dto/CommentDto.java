package com.select.choice.domain.comment.data.dto;

import com.select.choice.domain.post.domain.data.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class CommentDto {
    private final String content;
}
