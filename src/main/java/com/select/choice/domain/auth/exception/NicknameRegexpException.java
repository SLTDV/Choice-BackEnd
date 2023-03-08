package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;

public class NicknameRegexpException extends GlobalExceptions {
    public NicknameRegexpException(ErrorCode errorCode) {
        super(errorCode);
    }
}
