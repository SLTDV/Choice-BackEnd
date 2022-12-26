package com.select.choice.domain.comment.exception;

import com.select.choice.global.error.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}
