package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.dto.request.SignUpRequest;

public interface SignUpService {
    void signUp(SignUpRequest signUpRequest);
}
