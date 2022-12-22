package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;

public interface AuthService {
    TokenDto refresh(String refreshToken);
    TokenDto signIn(SignInRequest signInRequest);
    void signUp(SignUpRequest signUpRequest);
    void logout(String token);
}
