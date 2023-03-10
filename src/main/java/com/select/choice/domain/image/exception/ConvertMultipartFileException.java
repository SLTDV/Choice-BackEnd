package com.select.choice.domain.image.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class ConvertMultipartFileException extends GlobalException {
    public ConvertMultipartFileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
