package com.select.choice.domain.comment.util;

import com.select.choice.domain.comment.data.dto.CommentDto;
import com.select.choice.domain.comment.data.request.WriteCommentRequest;
import com.select.choice.domain.comment.entity.Comment;
import com.select.choice.domain.user.entity.User;

public interface CommentConverter {
    Comment toEntity(CommentDto commentDto, User user);

    CommentDto toDto(Long postIdx, WriteCommentRequest writeCommentRequest);
}