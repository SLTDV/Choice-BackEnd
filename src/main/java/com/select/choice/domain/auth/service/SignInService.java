package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.request.SignInRequest;
import com.select.choice.domain.auth.presentation.response.SignInResponse;

public interface SignInService {
    SignInResponse signIn(SignInRequest signInRequest);
}
