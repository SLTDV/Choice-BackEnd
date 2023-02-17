package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.data.dto.SignInDto;
import com.select.choice.domain.auth.data.dto.SignUpDto;
import com.select.choice.domain.auth.data.response.TokenResponse;

public interface AuthService {
    TokenResponse refresh(String refreshToken);
    TokenResponse signIn(SignInDto signInDto);
    void signUp(SignUpDto signUpDto);
    void logout(String token);
}
