package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class SignUpRequest {
    private final String email;
    private final String password;
    private final String nickname;
}
