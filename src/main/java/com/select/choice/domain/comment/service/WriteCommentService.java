package com.select.choice.domain.comment.service;

import com.select.choice.domain.comment.presentation.data.dto.CommentDto;

public interface WriteCommentService {
    void writeComment(Long postIdx, CommentDto commentDto);
}
