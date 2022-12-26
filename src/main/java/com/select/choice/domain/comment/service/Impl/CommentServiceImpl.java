package com.select.choice.domain.comment.service.Impl;

import com.select.choice.domain.comment.data.dto.CommentDto;
import com.select.choice.domain.comment.entity.Comment;
import com.select.choice.domain.comment.repository.CommentRepository;
import com.select.choice.domain.comment.service.CommentService;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.exception.PostNotFoundException;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserFacade userFacade;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public void write(Long postIdx,CommentDto commentDto) {
        User user = userFacade.currentUser();
        Post post = postRepository.findById(postIdx).orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        Comment comment = commentConverter.toEntity(commentDto, user, post);
        commentRepository.save(comment);
    }
}
