package com.select.choice.domain.comment.service;

import com.select.choice.domain.comment.presentation.data.dto.CommentDto;

public interface EditCommentService {
    void editComment(Long commentIdx, CommentDto commentDto);
}
