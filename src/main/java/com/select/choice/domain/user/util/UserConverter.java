package com.select.choice.domain.user.util;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.user.presentation.data.dto.ChangeProfileImageDto;
import com.select.choice.domain.user.presentation.data.dto.MyPageDto;
import com.select.choice.domain.user.presentation.data.dto.NicknameDto;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.presentation.data.request.ChangeProfileImageRequest;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;

import java.util.List;

public interface UserConverter {
    MyPageDto toDto(User user, List<Post> postList);
    GetMyPageResponse toResponse(MyPageDto myPageDto);
    NicknameDto toDto(ChangeNicknameRequest changeNicknameRequest);
    ChangeProfileImageDto toDto(ChangeProfileImageRequest changeProfileImageRequest);
}
