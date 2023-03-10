package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class IllegalArgumentJwtTokenException extends GlobalException {
    public IllegalArgumentJwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
