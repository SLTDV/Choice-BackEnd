package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class DuplicateNicknameException extends GlobalException {
    public DuplicateNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
