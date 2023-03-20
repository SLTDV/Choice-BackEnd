package com.select.choice.domain.comment.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class CommentNotFoundException extends GlobalException {
    public CommentNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
