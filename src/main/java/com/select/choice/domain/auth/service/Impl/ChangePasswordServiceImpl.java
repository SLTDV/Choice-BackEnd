package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.presentation.data.dto.ChangePasswordDto;
import com.select.choice.domain.auth.service.ChangePasswordService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserUtil userUtil;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void change(ChangePasswordDto changePasswordDto) {
        User user = userUtil.findUserByPhoneNumber(changePasswordDto.getPhoneNumber());
        user.changePassword(changePasswordDto.getPassword());
    }
}
