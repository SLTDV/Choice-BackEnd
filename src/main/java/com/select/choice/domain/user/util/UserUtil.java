package com.select.choice.domain.user.util;

import com.select.choice.domain.user.domain.entity.User;

public interface UserUtil {
    void checkPassword(User user, String password);
    User findUserByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    void save(User user);
    User currentUser();
    void withdrawal(User user);
}
