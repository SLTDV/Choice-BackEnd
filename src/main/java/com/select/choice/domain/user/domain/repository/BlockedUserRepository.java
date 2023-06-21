package com.select.choice.domain.user.domain.repository;

import com.select.choice.domain.user.domain.entity.BlockedUser;
import com.select.choice.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long> {
    BlockedUser findByBlockingUserIdxAndBlockedUserIdx(Long blockingUserIdx, Long blockedUserIdx);
    boolean existsByBlockingUserAndBlockedUser(User blockingUser, User blockedUser);
}
