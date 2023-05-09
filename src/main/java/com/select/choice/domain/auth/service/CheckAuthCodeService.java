package com.select.choice.domain.auth.service;

public interface CheckAuthCodeService {
    void checkAuthCode(String phoneNumber, String authCode);
}
