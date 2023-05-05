package com.select.choice.domain.user.Service.Impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.user.Service.GetMyPageService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.MyPageDto;
import com.select.choice.domain.user.util.UserConverter;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMyPageServiceImpl implements GetMyPageService {
    private final UserUtil userUtil;
    private final PostRepository postRepository;
    private final UserConverter userConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MyPageDto getMyPage() {
        User user = userUtil.currentUser();
        List<Post> postList = postRepository.findAllByUserIdx(user.getIdx());

        return userConverter.toDto(user, postList);
    }
}
