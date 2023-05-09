package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.entity.AuthCode;
import com.select.choice.domain.auth.domain.entity.Authentication;
import com.select.choice.domain.auth.domain.repository.AuthCodeRepository;
import com.select.choice.domain.auth.domain.repository.AuthenticationRepository;
import com.select.choice.domain.auth.exception.AuthCodeNotFoundException;
import com.select.choice.domain.auth.exception.InValidAuthCodeException;
import com.select.choice.domain.auth.service.CheckAuthCodeService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckAuthCodeServiceImpl implements CheckAuthCodeService {
    private final AuthCodeRepository authCodeRepository;
    private final AuthConverter authConverter;
    private final AuthenticationRepository authenticationRepository;

    @Override
    public void checkAuthCode(String phoneNumber, String authCode) {
        AuthCode entity = authCodeRepository.findById(phoneNumber)
                .orElseThrow(() -> new AuthCodeNotFoundException(ErrorCode.AUTH_CODE_NOT_FOUND));

        if(!authCode.equals(entity.getCode())) {
            throw new InValidAuthCodeException(ErrorCode.INVALID_AUTH_CODE);
        }

        Authentication authentication = authConverter.toEntity(phoneNumber);
        authenticationRepository.save(authentication);
    }
}
