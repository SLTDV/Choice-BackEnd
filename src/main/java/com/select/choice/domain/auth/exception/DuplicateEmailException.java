package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;

public class DuplicateEmailException extends GlobalExceptions {
    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
