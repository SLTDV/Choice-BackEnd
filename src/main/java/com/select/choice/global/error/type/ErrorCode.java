package com.select.choice.global.error.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 403),
    EXPIRED_TOKEN("만료된 토큰 입니다.", 403),

    // User
    USER_NOT_FOUND("존재하지 않은 USER 입니다.", 404);

    private final String message;
    private final int status;
}
