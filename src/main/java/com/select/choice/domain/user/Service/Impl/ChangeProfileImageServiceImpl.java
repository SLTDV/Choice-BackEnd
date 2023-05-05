package com.select.choice.domain.user.Service.Impl;

import com.select.choice.domain.user.Service.ChangeProfileImageService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.ChangeProfileImageDto;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeProfileImageServiceImpl implements ChangeProfileImageService {
    private final UserUtil userUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeProfileImage(ChangeProfileImageDto changeProfileImageDto) {
        User user = userUtil.currentUser();
        user.updateProfileImage(changeProfileImageDto.getImage());
    }
}
