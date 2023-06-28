package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.exception.PostNotFoundException;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.domain.repository.BlockedUserRepository;
import com.select.choice.domain.user.exception.BlockUserSelfException;
import com.select.choice.domain.user.exception.DuplicateBlockedUserException;
import com.select.choice.domain.user.service.BlockUserService;
import com.select.choice.domain.user.util.UserConverter;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockUserServiceImpl implements BlockUserService {
    private final BlockedUserRepository blockedUserRepository;
    private final UserUtil userUtil;
    private final UserConverter userConverter;
    private final PostRepository postRepository;

    @Override
    public void block(Long postIdx) {
        User blockingUser = userUtil.currentUser();
        User blockedUser = postRepository.findById(postIdx)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND))
                .getUser();
        if(blockedUser.equals(blockingUser))
            throw new BlockUserSelfException(ErrorCode.BLOCK_USER_SELF);
        else if(blockedUserRepository.existsByBlockingUserAndBlockedUser(blockingUser, blockedUser))
            throw new DuplicateBlockedUserException(ErrorCode.DUPLICATE_BLOCKED_USER);

        blockedUserRepository.save(userConverter.toEntity(blockingUser, blockedUser));
    }
}
