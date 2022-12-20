package com.select.choice.domain.auth.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private String checkpassword;
    private String nickname;
}
