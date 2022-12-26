package com.select.choice.domain.comment.util.Impl;

import com.select.choice.domain.comment.data.dto.CommentDto;
import com.select.choice.domain.comment.data.entity.Comment;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverterImpl implements CommentConverter {
    @Override
    public Comment toEntity(CommentDto commentDto, User user, Post post) {
        String content = commentDto.getContent();

        return Comment.builder()
                .post(post)
                .content(content)
                .user(user)
                .build();
    }

    @Override
    public CommentDto toDto(String content) {
        return CommentDto.builder()
                .content(content)
                .build();
    }
}
