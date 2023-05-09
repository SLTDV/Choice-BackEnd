package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.data.dto.TokenDto;

public interface RefreshService {
    TokenDto refresh(String refreshToken);
}
