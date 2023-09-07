package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class PushAlaramNotFoundException extends GlobalException {
    public PushAlaramNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
