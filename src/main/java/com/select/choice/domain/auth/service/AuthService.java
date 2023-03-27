package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.data.dto.SendPhoneNumberDto;
import com.select.choice.domain.auth.presentation.data.dto.SignInDto;
import com.select.choice.domain.auth.presentation.data.dto.SignUpDto;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface AuthService {
    TokenDto refresh(String refreshToken);
    TokenDto signIn(SignInDto signInDto);
    void signUp(SignUpDto signUpDto);
    void logout(String token);
    void sendSMS(SendPhoneNumberDto dto) throws CoolsmsException;
}
