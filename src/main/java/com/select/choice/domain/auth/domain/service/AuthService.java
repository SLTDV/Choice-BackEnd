package com.select.choice.domain.auth.domain.service;

import com.select.choice.domain.auth.domain.data.dto.SignInDto;
import com.select.choice.domain.auth.domain.data.dto.SignUpDto;
import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.response.TokenResponse;

public interface AuthService {
    TokenDto refresh(String refreshToken);
    TokenResponse signIn(SignInDto signInDto);
    void signUp(SignUpDto signUpDto);
    void logout(String token);
}
