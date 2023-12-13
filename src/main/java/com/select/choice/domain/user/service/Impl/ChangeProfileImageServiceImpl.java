package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.domain.repository.UserRepository;
import com.select.choice.domain.user.service.ChangeProfileImageService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.ChangeProfileImageDto;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeProfileImageServiceImpl implements ChangeProfileImageService {
    private final UserUtil userUtil;
    private final UserRepository userRepository;

    @Override
    public void changeProfileImage(ChangeProfileImageDto changeProfileImageDto) {
        User user = userUtil.currentUser();

        user.updateProfileImage(changeProfileImageDto.getImage());

        userRepository.save(user);
    }
}
