package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class NicknameRegexpException extends GlobalException {
    public NicknameRegexpException(ErrorCode errorCode) {
        super(errorCode);
    }
}
