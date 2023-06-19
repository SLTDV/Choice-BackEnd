package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.domain.repository.BlockRepository;
import com.select.choice.domain.user.domain.repository.UserRepository;
import com.select.choice.domain.user.exception.BlockUserSelfException;
import com.select.choice.domain.user.exception.UserNotFoundException;
import com.select.choice.domain.user.service.BlockUserService;
import com.select.choice.domain.user.util.UserConverter;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockUserServiceImpl implements BlockUserService {
    private final BlockRepository blockRepository;
    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public void block(Long userIdx) {
        User blockingUser = userUtil.currentUser();
        User blockedUser = userRepository.findById(userIdx)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        if(blockedUser.equals(blockingUser))
            throw new BlockUserSelfException(ErrorCode.BLOCK_USER_SELF);

        blockRepository.save(userConverter.toEntity(blockingUser, blockedUser));
    }
}
