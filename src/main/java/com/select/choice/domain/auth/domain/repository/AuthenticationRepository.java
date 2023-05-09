package com.select.choice.domain.auth.domain.repository;

import com.select.choice.domain.auth.domain.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication, String> {
}
