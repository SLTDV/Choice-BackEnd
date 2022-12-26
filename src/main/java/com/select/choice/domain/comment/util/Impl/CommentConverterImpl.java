package com.select.choice.domain.comment.util.Impl;

import com.select.choice.domain.comment.data.dto.CommentDto;
import com.select.choice.domain.comment.data.request.WriteCommentRequest;
import com.select.choice.domain.comment.entity.Comment;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.exception.PostNotFoundException;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.user.entity.User;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverterImpl implements CommentConverter {
    private final PostRepository postRepository;
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
    public CommentDto toDto(WriteCommentRequest writeCommentRequest) {
        String content = writeCommentRequest.getContent();

        return CommentDto.builder()
                .content(content)
                .build();
    }
}
