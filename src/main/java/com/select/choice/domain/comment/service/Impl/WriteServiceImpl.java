package com.select.choice.domain.comment.service.Impl;

import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.presentation.data.dto.CommentDto;
import com.select.choice.domain.comment.service.WriteService;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.util.PostUtil;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteServiceImpl implements WriteService {
    private final UserUtil userUtil;
    private final PostUtil postUtil;
    private final CommentConverter commentConverter;
    private final CommentRepository commentRepository;

    @Override
    public void write(Long postIdx, CommentDto commentDto) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);
        Comment comment = commentConverter.toEntity(commentDto, user, post);
        commentRepository.save(comment);
    }
}
