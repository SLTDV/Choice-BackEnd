package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;

public class PasswordRegexpException extends GlobalExceptions {
    public PasswordRegexpException(ErrorCode errorCode) {
        super(errorCode);
    }
}
