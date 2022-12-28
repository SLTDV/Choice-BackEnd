package com.select.choice.domain.comment.domain.util;

import com.select.choice.domain.comment.domain.data.dto.CommentDto;
import com.select.choice.domain.comment.domain.data.entity.Comment;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.domain.data.entity.User;

public interface CommentConverter {
    Comment toEntity(CommentDto commentDto, User user, Post post);

    CommentDto toDto(String content);
}
