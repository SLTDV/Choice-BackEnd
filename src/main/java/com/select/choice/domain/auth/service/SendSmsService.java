package com.select.choice.domain.auth.service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface SendSmsService {
    void sendSMS(String phoneNumber) throws CoolsmsException;
}
