package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.domain.repository.UserRepository;
import com.select.choice.domain.user.service.ChangeNicknameService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.NicknameDto;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeNicknameServiceImpl implements ChangeNicknameService {
    private final UserUtil userUtil;
    private final UserRepository userRepository;

    @Override
    public void changeNickname(NicknameDto nicknameDto) {
        User user = userUtil.currentUser();

        user.updateNickname(nicknameDto.getNickname());

        userRepository.save(user);
    }
}
