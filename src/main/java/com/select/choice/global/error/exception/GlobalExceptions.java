package com.select.choice.global.error.exception;

import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalExceptions extends RuntimeException{
    private final ErrorCode errorCode;
}
