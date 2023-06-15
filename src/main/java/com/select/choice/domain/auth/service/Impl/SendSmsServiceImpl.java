package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.repository.AuthCodeRepository;
import com.select.choice.domain.auth.exception.DuplicatePhoneNumberException;
import com.select.choice.domain.auth.exception.UnregisterdPhoneNumberException;
import com.select.choice.domain.auth.properties.CoolSMSProperties;
import com.select.choice.domain.auth.service.SendSmsService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import com.twilio.Twilio;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendSmsServiceImpl implements SendSmsService {
    private final UserUtil userUtil;
    private final CoolSMSProperties coolSMSProperties;
    private final AuthCodeRepository authCodeRepository;
    private final AuthConverter authConverter;

    @Value("${twillio.account_sid}")
    private String accountSid;

    @Value("${twillio.auth_token}")
    private String authToken;

    @Override
    public void sendSMS(String phoneNumber) throws CoolsmsException {
        if(userUtil.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicatePhoneNumberException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        }

        String authCode = createAuthCode();
        twillio(authCode, phoneNumber);

        authCodeRepository.save(authConverter.toEntity(authCode, phoneNumber));
    }

    @Override
    public void send(String phoneNumber) throws CoolsmsException {
        if(!userUtil.existsByPhoneNumber(phoneNumber)) {
            throw new UnregisterdPhoneNumberException(ErrorCode.UNREGISTERED_PHONE_NUMBER);
        }

        String authCode = createAuthCode();
        twillio(authCode, phoneNumber);

        authCodeRepository.save(authConverter.toEntity(authCode, phoneNumber));
    }

    private String createAuthCode() {
        Random rand  = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }
        return numStr.toString();
    }

    private void twillio(String authCode, String phoneNumber) {
        if(phoneNumber.startsWith("010")) {
            phoneNumber = "+82" + phoneNumber.substring(1);
        }
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber("+14302336938"),
                "초이스 본인확인 인증번호(" + authCode + ") 입력시 정상처리 됩니다."
        ).create();
    }

}
