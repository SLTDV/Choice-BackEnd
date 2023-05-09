package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.user.service.WithdrawalService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService {
    private final UserUtil userUtil;

    @Override
    public void withdrawal() {
        User user = userUtil.currentUser();
        userUtil.withdrawal(user);
    }
}
