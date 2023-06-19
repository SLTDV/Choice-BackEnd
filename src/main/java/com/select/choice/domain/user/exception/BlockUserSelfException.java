package com.select.choice.domain.user.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class BlockUserSelfException extends GlobalException {
    public BlockUserSelfException(ErrorCode errorCode) {
        super(errorCode);
    }
}
