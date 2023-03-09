package com.select.choice.domain.post.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PostNotFoundException extends GlobalExceptions {
    public PostNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
