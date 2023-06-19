package com.select.choice.domain.user.service.Impl;

import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.exception.PostNotFoundException;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.domain.repository.BlockedUserRepository;
import com.select.choice.domain.user.service.UnBlockUserService;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnBlockUserServiceImpl implements UnBlockUserService {
    private final BlockedUserRepository blockedUserRepository;
    private final UserUtil userUtil;
    private final PostRepository postRepository;
    @Override
    public void unBlock(Long postIdx) {
        User currentUser = userUtil.currentUser();
        User user = postRepository.findById(postIdx)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND))
                .getUser();
        blockedUserRepository.delete(blockedUserRepository.findByBlockingUserIdxAndBlockedUserIdx(currentUser.getIdx(), user.getIdx()));
    }
}
