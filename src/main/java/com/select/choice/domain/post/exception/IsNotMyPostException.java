package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class IsNotMyPostException extends GlobalException {
    public IsNotMyPostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
