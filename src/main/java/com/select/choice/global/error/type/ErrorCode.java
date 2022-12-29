package com.select.choice.global.error.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_NOT_MATCH("비밀번호가 옳지 않습니다.", 400),
    IS_NOT_MY_COMMENT("자신의 댓글이 아닙니다.", 400),
    EMAIL_REGEXP("이메일 형식에 알맞지 않습니다.", 400),
    PASSWORD_REGEXP("비밀번호 형식에 알맞지 않습니다.", 400),
    IS_NOT_MY_POST("당신의 게시물이 아닙니다.", 400),
    NICKNAME_REGEXP("닉네임 형식에 알맞지 않습니다.", 400),
    UNAUTHORIZED("UNAUTHORIZED", 401),
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 403),
    EXPIRED_TOKEN("만료된 토큰 입니다.", 403),
    POST_NOT_FOUND("존재하지 않은 POST 입니다.", 404),
    USER_NOT_FOUND("존재하지 않은 USER 입니다.", 404),
    Comment_NOT_FOUND("존재하지 않은 COMMENT 입니다.", 404),
    DUPLICATE_EMAIL("이미 존재하는 이메일 입니다.", 409),
    DUPLICATE_NICKNAME("이미 존재하는 닉네임 입니다.", 409);

    private final String message;
    private final int status;
}
