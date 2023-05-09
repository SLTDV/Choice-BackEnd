package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class InValidAuthCodeException extends GlobalException {
    public InValidAuthCodeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
