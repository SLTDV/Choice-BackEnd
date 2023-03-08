package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;

public class IllegalArgumentJwtTokenException extends GlobalExceptions {
    public IllegalArgumentJwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
