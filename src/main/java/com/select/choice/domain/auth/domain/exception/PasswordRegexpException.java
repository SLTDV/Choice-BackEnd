package com.select.choice.domain.auth.domain.exception;

import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PasswordRegexpException extends RuntimeException {
    private final ErrorCode errorCode;
}
