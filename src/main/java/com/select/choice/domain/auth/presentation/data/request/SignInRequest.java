package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class SignInRequest {
    private final String phoneNumber;
    private final String password;
    private final String fcmToken;
}
