package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.exception.IsNotMyPostException;
import com.select.choice.domain.post.service.DeletePostService;
import com.select.choice.domain.post.util.PostUtil;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeletePostServiceImpl implements DeletePostService {
    private final UserUtil userUtil;
    private final PostUtil postUtil;
    private final PostRepository postRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePost(Long postIdx) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);

        if(!Objects.equals(post.getUser(), user))
            throw new IsNotMyPostException(ErrorCode.IS_NOT_MY_POST);

        postRepository.delete(post);
    }
}
