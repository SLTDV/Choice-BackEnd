package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {
    private final String phoneNumber;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private final String password;
    @Size(min = 2, max = 6, message = "닉네임은 2글자 이상 6글자 이하입니다.")
    private final String nickname;
    private final Optional<String> profileImgUrl;
}
