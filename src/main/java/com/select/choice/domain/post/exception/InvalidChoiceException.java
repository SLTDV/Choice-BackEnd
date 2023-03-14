package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class InvalidChoiceException extends GlobalException {
    public InvalidChoiceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
