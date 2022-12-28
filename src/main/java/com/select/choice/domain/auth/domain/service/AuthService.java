package com.select.choice.domain.auth.domain.service;

import com.select.choice.domain.auth.domain.data.dto.SignInDto;
import com.select.choice.domain.auth.domain.data.dto.SignUpDto;
import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.request.SignUpRequest;

public interface AuthService {
    TokenDto refresh(String refreshToken);
    TokenDto signIn(SignInDto signInDto);
    void signUp(SignUpDto signUpDto);
    void logout(String token);
}
