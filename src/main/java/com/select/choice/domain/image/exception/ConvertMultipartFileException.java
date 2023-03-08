package com.select.choice.domain.image.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;

public class ConvertMultipartFileException extends GlobalExceptions {
    public ConvertMultipartFileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
