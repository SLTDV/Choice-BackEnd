package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.data.dto.SignUpDto;

public interface SignUpService {
    void signUp(SignUpDto signUpDto);
}
