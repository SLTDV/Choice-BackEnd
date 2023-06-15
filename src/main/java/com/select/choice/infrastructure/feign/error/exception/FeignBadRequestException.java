package com.select.choice.infrastructure.feign.error.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class FeignBadRequestException extends GlobalException {
    public FeignBadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
