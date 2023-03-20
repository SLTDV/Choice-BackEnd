package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class PostNotFoundException extends GlobalException {
    public PostNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
