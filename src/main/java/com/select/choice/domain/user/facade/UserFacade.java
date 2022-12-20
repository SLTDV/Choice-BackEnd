package com.select.choice.domain.user.facade;

import com.select.choice.domain.auth.presentation.request.SignUpRequest;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.auth.exception.PasswordNotMatchException;
import com.select.choice.domain.user.exception.UserNotFoundException;
import com.select.choice.domain.user.repository.UserRepository;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserFacade {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkPassword(User user, String password) {
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(()
                -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(SignUpRequest signUpRequest) {
        System.out.println(signUpRequest.getNickname());
        User user = new User(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getNickname());
        userRepository.save(user);
    }
}
