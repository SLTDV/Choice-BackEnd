package com.select.choice.global.error.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 403),
    EXPIRED_TOKEN("만료된 토큰 입니다.", 403),

    // AUTH
    PASSWORD_NOT_MATCH("비밀번호가 옳지 않습니다.", 400),
    DUPLICATE_EMAIL("이미 존재하는 이메일 입니다.", 409),
    CHECK_PASSWORD_NOT_MATCH("비밀번호를 다시 확인해주세요", 400),

    // USER
    USER_NOT_FOUND("존재하지 않은 USER 입니다.", 404),
    DUPLICATE_NICKNAME("이미 존재하는 닉네임 입니다.", 409);


    private final String message;
    private final int status;
}
