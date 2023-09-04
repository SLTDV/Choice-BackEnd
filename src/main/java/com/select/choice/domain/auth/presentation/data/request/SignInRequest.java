package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class SignInRequest {
    private final String phoneNumber;
    private final String password;
    private final Optional<String> deviceToken;
}
