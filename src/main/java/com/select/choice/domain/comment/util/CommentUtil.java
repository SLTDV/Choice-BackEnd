package com.select.choice.domain.comment.util;

import com.select.choice.domain.comment.domain.entity.Comment;

public interface CommentUtil {
    Comment findById(Long commentIdx);
}
