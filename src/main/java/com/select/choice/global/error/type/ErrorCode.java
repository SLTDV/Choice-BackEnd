package com.select.choice.global.error.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_NOT_MATCH("비밀번호가 옳지 않습니다.", 400),
    CONVERT_MULTIPART_FILE("file 로 타입 변환 실패", 400),
    INVALID_CHOICE("유효하지 않는 choice 입니다.", 400),
    INVALID_VOTE_COUNT("유효하지 않는 투표 count 값입니다.", 400),
    NICKNAME_REGEXP("올바르지 않는 닉네임입니다.", 400),
    INVALID_AUTH_CODE("유효하지 않는 인증 코드 입니다.", 400),
    PASSWORD_ORIGINAL("원래 사용했던 비밀번호 입니다.", 400),
    UNAUTHORIZED("UNAUTHORIZED", 401),
    INVALID_JWT_SIGNATURE("올바르지 않는 서명입니다.", 401),
    UNSUPPORTED_JWT("지원되지 않는 JWT 토큰입니다.", 401),
    ILLEGAL_ARGUMENT_JWT("JWT 토큰이 잘못되었습니다.", 401),
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    EXPIRED_TOKEN("만료된 토큰 입니다.", 401),
    UNREGISTERED_PHONE_NUMBER("회원가입 하지 않은 전화번호 입니다.", 409),
    IS_NOT_MY_COMMENT("자신의 댓글이 아닙니다.", 403),
    IS_NOT_MY_POST("자신의 게시물이 아닙니다.", 403),
    NOT_REGISTERED_PHONE_NUMBER("인증하지 않은 전화번호입니다.", 403),
    POST_NOT_FOUND("존재하지 않은 POST 입니다.", 404),
    USER_NOT_FOUND("존재하지 않은 USER 입니다.", 404),
    Comment_NOT_FOUND("존재하지 않은 COMMENT 입니다.", 404),
    AUTH_CODE_NOT_FOUND("존재하지 않는 AuthCode 입니다.", 404),
    DUPLICATE_PHONE_NUMBER("이미 존재하는 휴대전화 번호입니다.", 409),
    DUPLICATE_NICKNAME("이미 존재하는 닉네임 입니다.", 409),
    ALREADY_REPORT_POST("이미 신고한 게시물 입니다.", 409);

    private final String message;
    private final int status;
}
