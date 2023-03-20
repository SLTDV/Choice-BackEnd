package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class PasswordRegexpException extends GlobalException {
    public PasswordRegexpException(ErrorCode errorCode) {
        super(errorCode);
    }
}
