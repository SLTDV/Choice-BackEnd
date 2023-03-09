package com.select.choice.domain.user.Service.Impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.repository.PostRepository;
import com.select.choice.domain.user.Service.UserService;
import com.select.choice.domain.user.data.dto.MyPageDto;
import com.select.choice.domain.user.data.dto.NicknameDto;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.data.response.GetMyPageResponse;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.domain.user.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserFacade userFacade;
    private final PostRepository postRepository;
    private final UserConverter userConverter;

    @Override
    public void delete() {
        User user = userFacade.currentUser();
        userFacade.deleteUser(user);
    }

    @Override
    public GetMyPageResponse getMyPage() {
        User user = userFacade.currentUser();
        List<Post> postList = postRepository.findAllByUserIdx(user.getIdx());

        MyPageDto myPageDto = userConverter.toDto(user, postList);

        return userConverter.toResponse(myPageDto);
    }

    @Transactional
    @Override
    public void changeNickname(NicknameDto nicknameDto) {
        User user = userFacade.currentUser();
        user.updateNickname(nicknameDto.getNickname());
    }
}
