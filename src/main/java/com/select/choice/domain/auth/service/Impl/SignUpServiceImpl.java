package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.exception.CheckPasswordNotMatch;
import com.select.choice.domain.auth.presentation.request.SignUpRequest;
import com.select.choice.domain.auth.service.SignUpService;
import com.select.choice.domain.auth.exception.DuplicateEmailException;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final UserFacade userFacade;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if(userFacade.existsByEmail(signUpRequest.getEmail())) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }else if(!signUpRequest.getPassword().equals(signUpRequest.getCheckpassword())){
            throw new CheckPasswordNotMatch(ErrorCode.CHECK_PASSWORD_NOT_MATCH);
        }
        userFacade.save(signUpRequest);
    }
}
