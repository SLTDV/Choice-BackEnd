package com.select.choice.domain.comment.util;

import com.select.choice.domain.comment.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.data.dto.CommentDto;
import com.select.choice.domain.comment.data.entity.Comment;
import com.select.choice.domain.comment.data.request.EditCommentRequest;
import com.select.choice.domain.comment.data.request.WriteCommentRequest;
import com.select.choice.domain.post.data.entity.Post;
import com.select.choice.domain.user.data.entity.User;

import java.util.List;

public interface CommentConverter {
    Comment toEntity(CommentDto commentDto, User user, Post post);

    CommentDto toDto(EditCommentRequest editCommentRequest);
    CommentDto toDto(WriteCommentRequest writeCommentRequest);

    List<CommentDetailDto> toDto(List<Comment> comment);

}
