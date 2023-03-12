package com.select.choice.domain.comment.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class IsNotMyCommentException extends GlobalException {
    public IsNotMyCommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
