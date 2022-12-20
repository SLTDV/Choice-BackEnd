package com.select.choice.domain.auth.exception;

import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateEmailException extends RuntimeException {
    private final ErrorCode errorCode;
}
