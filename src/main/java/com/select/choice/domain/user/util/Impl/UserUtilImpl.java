package com.select.choice.domain.user.util.Impl;

import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.auth.exception.PasswordNotMatchException;
import com.select.choice.domain.user.exception.UserNotFoundException;
import com.select.choice.domain.user.domain.repository.UserRepository;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserUtilImpl implements UserUtil {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkPassword(User user, String password) {
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(()
                -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User currentUser(){
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByPhoneNumber(phoneNumber);
    }

    public void withdrawal(User user){
        userRepository.delete(user);
    }
}
