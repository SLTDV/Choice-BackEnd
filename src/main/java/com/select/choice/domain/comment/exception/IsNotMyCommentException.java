package com.select.choice.domain.comment.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;

public class IsNotMyCommentException extends GlobalExceptions {
    public IsNotMyCommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
