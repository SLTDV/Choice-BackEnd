package com.select.choice.domain.user.repository;

import com.select.choice.domain.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
