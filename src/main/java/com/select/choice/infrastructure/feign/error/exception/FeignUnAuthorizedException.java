package com.select.choice.infrastructure.feign.error.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class FeignUnAuthorizedException extends GlobalException {
    public FeignUnAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
