package com.select.choice.domain.post.presentation.data.response;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailResponse {
    private String authorname;
    private List<CommentDetailDto> comment;
}
