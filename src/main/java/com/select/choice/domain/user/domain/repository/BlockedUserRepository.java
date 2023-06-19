package com.select.choice.domain.user.domain.repository;

import com.select.choice.domain.user.domain.entity.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long> {
    BlockedUser findByBlockingUserIdxAndBlockedUserIdx(Long blockingUserIdx, Long blockedUserIdx);
}
