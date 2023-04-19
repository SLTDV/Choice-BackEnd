package com.select.choice.domain.user.util;

import com.select.choice.domain.user.domain.entity.User;

public interface UserUtil {
    void checkPassword(User user, String password);
    User findUserByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String PhoneNumber);
    boolean existsByNickname(String nickname);
    void save(User user);
    User currentUser();
    void deleteUser(User user);
}
