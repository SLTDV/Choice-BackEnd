package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.data.response.RefreshTokenResponse;

public interface AuthService {
    RefreshTokenResponse refresh(String refreshToken);
    TokenDto signIn(SignInRequest signInRequest);
    void signUp(SignUpRequest signUpRequest);

}
