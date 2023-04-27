package com.select.choice.domain.comment.service.Impl;

import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.exception.IsNotMyCommentException;
import com.select.choice.domain.comment.service.DeleteService;
import com.select.choice.domain.comment.util.CommentUtil;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteServiceImpl implements DeleteService {
    private final UserUtil userUtil;
    private final CommentUtil commentUtil;
    private final CommentRepository commentRepository;

    @Override
    public void delete(Long commentIdx) {
        User user = userUtil.currentUser();
        Comment comment = commentUtil.findById(commentIdx);

        if(!comment.getUser().equals(user)){
            throw new IsNotMyCommentException(ErrorCode.IS_NOT_MY_COMMENT);
        }

        commentRepository.delete(comment);
    }
}
