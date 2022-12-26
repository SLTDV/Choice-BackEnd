package com.select.choice.domain.user.Service.Impl;

import com.select.choice.domain.user.Service.UserService;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserFacade userFacade;

    @Override
    public void delete() {
        User user = userFacade.currentUser();
        userFacade.deleteUser(user);
    }
}
