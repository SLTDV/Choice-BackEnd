package com.select.choice.domain.comment.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentDetailDto {
    private final Long idx;
    private final String content;
    private final String nickname;
}
