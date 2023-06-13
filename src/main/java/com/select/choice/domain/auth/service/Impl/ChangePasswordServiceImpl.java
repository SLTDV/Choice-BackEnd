package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.exception.PasswordOriginalException;
import com.select.choice.domain.auth.presentation.data.dto.ChangePasswordDto;
import com.select.choice.domain.auth.service.ChangePasswordService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void change(ChangePasswordDto changePasswordDto) {
        User user = userUtil.findUserByPhoneNumber(changePasswordDto.getPhoneNumber());
        if(passwordEncoder.matches(changePasswordDto.getPassword(), user.getPassword()))
            throw new PasswordOriginalException(ErrorCode.PASSWORD_ORIGINAL);
        user.changePassword(passwordEncoder.encode(changePasswordDto.getPassword()));
    }
}
