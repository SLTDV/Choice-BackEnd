package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.service.GetHeaderService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.HeaderDto;
import com.select.choice.domain.user.util.UserConverter;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHeaderServiceImpl implements GetHeaderService {
    private final UserUtil userUtil;
    private final UserConverter userConverter;

    @Override
    public HeaderDto getHeader() {
        User user = userUtil.currentUser();
        return userConverter.toDto(user);
    }
}
