package com.select.choice.domain.comment.service.Impl;

import com.select.choice.domain.comment.presentation.data.dto.CommentDto;
import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.exception.IsNotMyCommentException;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.service.CommentService;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.comment.util.CommentUtil;
import com.select.choice.domain.post.data.entity.Post;
import com.select.choice.domain.post.exception.PostNotFoundException;
import com.select.choice.domain.post.repository.PostRepository;
import com.select.choice.domain.user.data.entity.User;
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
    private final CommentUtil commentUtil;

    @Transactional
    @Override
    public void write(Long postIdx,CommentDto commentDto) {
        User user = userFacade.currentUser();
        Post post = postRepository.findById(postIdx)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        Comment comment = commentConverter.toEntity(commentDto, user, post);
        commentRepository.save(comment);
    }

    @Override
    public void edit(Long commentIdx, CommentDto commentDto) {
        User user = userFacade.currentUser();
        Comment comment = commentUtil.findById(commentIdx);
        if(!comment.getUser().equals(user)){
            throw new IsNotMyCommentException(ErrorCode.IS_NOT_MY_COMMENT);
        }
        comment.updateContent(commentDto.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void delete(Long commentIdx) {
        Comment comment = commentUtil.findById(commentIdx);
        commentRepository.delete(comment);
    }
}
