package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.data.dto.SignInDto;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
