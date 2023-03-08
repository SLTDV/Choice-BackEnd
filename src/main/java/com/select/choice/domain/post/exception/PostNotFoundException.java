package com.select.choice.domain.post.exception;

import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
}