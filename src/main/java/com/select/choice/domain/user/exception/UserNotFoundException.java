package com.select.choice.domain.user.exception;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class UserNotFoundException extends GlobalExceptions {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
