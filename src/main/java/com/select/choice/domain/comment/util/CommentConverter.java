package com.select.choice.domain.comment.util;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.presentation.data.dto.CommentDto;
import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.presentation.data.request.EditCommentRequest;
import com.select.choice.domain.comment.presentation.data.request.WriteCommentRequest;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.PostVotingStatus;
import com.select.choice.domain.user.domain.entity.User;

import java.util.List;

public interface CommentConverter {
    Comment toEntity(CommentDto commentDto, User user, Post post);
    CommentDto toDto(EditCommentRequest editCommentRequest);
    CommentDto toDto(WriteCommentRequest writeCommentRequest);
    List<CommentDetailDto> toDto(List<Comment> comment);
    PostVotingStatus toEntity(int choiceOption, User user, Post post);

}
