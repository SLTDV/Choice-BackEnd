package com.select.choice.domain.comment.domain.util;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.domain.data.dto.CommentDto;
import com.select.choice.domain.comment.domain.data.entity.Comment;
import com.select.choice.domain.comment.domain.data.request.EditCommentRequest;
import com.select.choice.domain.comment.domain.data.request.WriteCommentRequest;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.domain.data.entity.User;

import java.util.List;

public interface CommentConverter {
    Comment toEntity(CommentDto commentDto, User user, Post post);

    CommentDto toDto(EditCommentRequest editCommentRequest);
    CommentDto toDto(WriteCommentRequest writeCommentRequest);

    List<CommentDetailDto> toDto(List<Comment> comment);

}
