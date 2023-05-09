package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.repository.AuthenticationRepository;
import com.select.choice.domain.auth.exception.DuplicateNicknameException;
import com.select.choice.domain.auth.exception.DuplicatePhoneNumberException;
import com.select.choice.domain.auth.exception.NicknameRegexpException;
import com.select.choice.domain.auth.exception.NotRegisteredPhoneNumberException;
import com.select.choice.domain.auth.presentation.data.dto.SignUpDto;
import com.select.choice.domain.auth.service.SignUpService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final AuthenticationRepository authenticationRepository;
    private final UserUtil userUtil;
    private final AuthConverter authConverter;

    @Override
    public void signUp(SignUpDto signUpDto) {
        if(userUtil.existsByPhoneNumber(signUpDto.getPhoneNumber())) {
            throw new DuplicatePhoneNumberException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        } else if(!authenticationRepository.existsById(signUpDto.getPhoneNumber())) {
            throw new NotRegisteredPhoneNumberException(ErrorCode.NOT_REGISTERED_PHONE_NUMBER);
        } else if (userUtil.existsByNickname(signUpDto.getNickname())) {
            throw new DuplicateNicknameException(ErrorCode.DUPLICATE_NICKNAME);
        } else if (signUpDto.getNickname().startsWith(" ")) {
            throw new NicknameRegexpException(ErrorCode.NICKNAME_REGEXP);
        }

        User user = authConverter.toEntity(signUpDto, signUpDto.getProfileImgUrl().isPresent());
        userUtil.save(user);
    }
}
