package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.dto.response.RefreshTokenResponse;

public interface RefreshTokenService {
    RefreshTokenResponse refresh(String refreshToken);
}
