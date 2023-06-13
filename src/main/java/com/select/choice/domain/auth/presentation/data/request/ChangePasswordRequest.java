package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Builder
@RequiredArgsConstructor
public class ChangePasswordRequest {
    private final String phoneNumber;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private final String password;
}
