package com.select.choice.domain.user.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class DuplicateBlockedUserException extends GlobalException {
    public DuplicateBlockedUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
