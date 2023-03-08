package com.select.choice.domain.comment.util;

import com.select.choice.domain.comment.data.entity.Comment;

public interface CommentUtil {
    Comment findById(Long commentIdx);
}
