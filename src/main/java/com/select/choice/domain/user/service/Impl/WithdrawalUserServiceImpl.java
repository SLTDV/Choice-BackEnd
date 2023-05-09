package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.service.WithdrawalUserService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawalUserServiceImpl implements WithdrawalUserService {
    private final UserUtil userUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdrawal() {
        User user = userUtil.currentUser();
        userUtil.withdrawal(user);
    }
}
