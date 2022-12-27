package com.select.choice.domain.auth.domain.service;

import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.request.SignInRequest;
import com.select.choice.domain.auth.domain.data.request.SignUpRequest;

public interface AuthService {
    TokenDto refresh(String refreshToken);
    TokenDto signIn(SignInRequest signInRequest);
    void signUp(SignUpRequest signUpRequest);
    void logout(String token);
}
