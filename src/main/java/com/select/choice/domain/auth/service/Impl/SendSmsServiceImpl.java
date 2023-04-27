package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.repository.AuthCodeRepository;
import com.select.choice.domain.auth.exception.DuplicatePhoneNumberException;
import com.select.choice.domain.auth.properties.CoolSMSProperties;
import com.select.choice.domain.auth.service.SendSmsService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendSmsServiceImpl implements SendSmsService {
    private final UserUtil userUtil;
    private final CoolSMSProperties coolSMSProperties;
    private final AuthCodeRepository authCodeRepository;
    private final AuthConverter authConverter;

    @Override
    public void sendSMS(String phoneNumber) throws CoolsmsException {
        if(userUtil.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicatePhoneNumberException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        }

        Message coolsms = new Message(coolSMSProperties.getApiKey(), coolSMSProperties.getApiSecret());

        Random rand  = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01055762163");    // 발신전화번호
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");

        coolsms.send(params);

        authCodeRepository.save(authConverter.toEntity(numStr.toString(), phoneNumber));

    }
}
