package com.select.choice.domain.auth.data.response;

import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private final String refreshToken;
    private final Long expiredAt;
}
