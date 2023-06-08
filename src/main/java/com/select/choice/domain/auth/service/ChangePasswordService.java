package com.select.choice.domain.auth.service;

import com.select.choice.domain.auth.presentation.data.dto.ChangePasswordDto;

public interface ChangePasswordService {
    void change(ChangePasswordDto changePasswordDto);
}
