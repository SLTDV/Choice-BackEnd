package com.select.choice.domain.comment.util.Impl;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.presentation.data.dto.CommentDto;
import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.presentation.data.request.EditCommentRequest;
import com.select.choice.domain.comment.presentation.data.request.WriteCommentRequest;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.VotingPost;
import com.select.choice.domain.user.domain.entity.User;
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
    public CommentDto toDto(EditCommentRequest editCommentRequest) {
        return CommentDto.builder()
                .content(editCommentRequest.getContent())
                .build();
    }

    @Override
    public CommentDto toDto(WriteCommentRequest writeCommentRequest) {
        return CommentDto.builder()
                .content(writeCommentRequest.getContent())
                .build();
    }

    @Override
    public List<CommentDetailDto> toDto(List<Comment> comment) {
        return comment.stream().map(list ->
                new CommentDetailDto(
                        list.getIdx(),
                        list.getContent(),
                        list.getUser().getNickname()
                )
        ).sorted(Comparator.comparing(CommentDetailDto::getIdx).reversed()).collect(Collectors.toList());
    }

    @Override
    public VotingPost toEntity(int choiceOption, User user, Post post) {
        return VotingPost.builder()
                .vote(choiceOption)
                .user(user)
                .post(post)
                .build();
    }
}
