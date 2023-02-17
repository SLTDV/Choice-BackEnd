package com.select.choice.domain.comment.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class CommentDetailDto {
    private final Long idx;
    private final String content;
    private final String nickname;
}
