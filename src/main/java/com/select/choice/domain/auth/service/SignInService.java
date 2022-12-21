package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.dto.request.SignInRequest;
import com.select.choice.domain.auth.presentation.dto.response.SignInResponse;

public interface SignInService {
    SignInResponse signIn(SignInRequest signInRequest);
}
