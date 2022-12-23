package com.select.choice.domain.auth.data.request;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Builder
@RequiredArgsConstructor
public class SignUpRequest {
    @Pattern(regexp = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식 x")
    private final String email;
    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호 형식 x")
    private final String password;
    private final String nickname;
}
