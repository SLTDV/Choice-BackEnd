package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalException;
import com.select.choice.global.error.type.ErrorCode;

public class AlreadyReportPostException extends GlobalException {
    public AlreadyReportPostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
