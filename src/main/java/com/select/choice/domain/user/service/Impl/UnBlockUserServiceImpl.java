package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.domain.repository.BlockedUserRepository;
import com.select.choice.domain.user.service.UnBlockUserService;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnBlockUserServiceImpl implements UnBlockUserService {
    private final BlockedUserRepository blockedUserRepository;
    private final UserUtil userUtil;
    @Override
    public void unBlock(Long userIdx) {
        User user = userUtil.currentUser();
        blockedUserRepository.delete(blockedUserRepository.findByBlockingUserIdxAndBlockedUserIdx(user.getIdx(), userIdx));
    }
}
