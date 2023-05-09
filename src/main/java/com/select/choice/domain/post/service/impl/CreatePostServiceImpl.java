package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.CreatePostDto;
import com.select.choice.domain.post.service.CreatePostService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostServiceImpl implements CreatePostService {
    private final UserUtil userUtil;
    private final PostConverter postConverter;
    private final PostRepository postRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPost(CreatePostDto postDto) {
        User currentUser = userUtil.currentUser();
        Post post = postConverter.toEntity(postDto, currentUser);
        postRepository.save(post);

    }
}
