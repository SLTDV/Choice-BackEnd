package com.select.choice.domain.user.Service.Impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.Service.GetWebMyPageService;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.WebMyPageDto;
import com.select.choice.domain.user.presentation.data.dto.WebPostDto;
import com.select.choice.domain.user.util.UserConverter;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetWebMyPageServiceImpl implements GetWebMyPageService {
    private final UserUtil userUtil;
    private final PostRepository postRepository;
    private final UserConverter userConverter;
    private final PostConverter postConverter;

    @Override
    public WebMyPageDto getMyPage() {
        User user = userUtil.currentUser();

        List<Post> postList = postRepository.findAllByUserIdx(user.getIdx());
        List<WebPostDto> webPostDtoList = postConverter.toWebPostDto(postList, user);

        return userConverter.toWebMyPageDto(user, webPostDtoList);
    }
}
