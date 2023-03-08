package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.data.dto.SignInDto;
import com.select.choice.domain.auth.presentation.data.dto.SignUpDto;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;
import com.select.choice.domain.auth.presentation.data.response.TokenResponse;

public interface AuthService {
    TokenResponse refresh(String refreshToken);
    TokenDto signIn(SignInDto signInDto);
    void signUp(SignUpDto signUpDto);
    void logout(String token);
}
