package com.select.choice.domain.comment.domain.util.Impl;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.domain.data.dto.CommentDto;
import com.select.choice.domain.comment.domain.data.entity.Comment;
import com.select.choice.domain.comment.domain.util.CommentConverter;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.domain.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDetailDto> toDetailDto(List<Comment> comment) {
        return comment.stream().map(list ->
                new CommentDetailDto(
                        list.getIdx(),
                        list.getContent(),
                        list.getUser().getNickname()
                )
        ).sorted(Comparator.comparing(CommentDetailDto::getIdx).reversed()).collect(Collectors.toList());
    }
}
