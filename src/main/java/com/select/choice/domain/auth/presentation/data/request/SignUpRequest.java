package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {
    @Pattern(regexp = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}", message = "이메일 형식이 올바르지 않습니다.")
    private final String email;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private final String password;
    @Size(min = 2, max = 6, message = "닉네임은 2글자 이상 6글자 이하입니다.")
    private final String nickname;
}
