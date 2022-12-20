package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.request.SignUpRequest;

public interface SignUpService {
    void signUp(SignUpRequest signUpRequest);
}
