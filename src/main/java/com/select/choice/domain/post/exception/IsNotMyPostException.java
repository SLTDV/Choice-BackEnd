package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class IsNotMyPostException extends GlobalExceptions {
    public IsNotMyPostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
