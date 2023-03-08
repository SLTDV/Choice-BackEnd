package com.select.choice.domain.comment.util.Impl;

import com.select.choice.domain.comment.data.entity.Comment;
import com.select.choice.domain.comment.exception.CommentNotFoundException;
import com.select.choice.domain.comment.repository.CommentRepository;
import com.select.choice.domain.comment.util.CommentUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUtilImpl implements CommentUtil {
    private final CommentRepository commentRepository;

    public Comment findById(Long commentIdx){
        return commentRepository.findById(commentIdx)
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.Comment_NOT_FOUND));
    }
}
