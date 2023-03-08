package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class SignInRequest {
    private final String email;
    private final String password;
}
