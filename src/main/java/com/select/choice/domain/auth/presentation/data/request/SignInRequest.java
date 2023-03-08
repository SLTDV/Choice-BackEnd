package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class SignInRequest {
    private final String email;
    private final String password;
}
