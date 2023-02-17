package com.select.choice.domain.user.util;

import com.select.choice.domain.post.data.entity.Post;
import com.select.choice.domain.user.data.dto.MyPageDto;
import com.select.choice.domain.user.data.dto.NicknameDto;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.data.response.GetMyPageResponse;

import java.util.List;

public interface UserConverter {
    MyPageDto toDto(User user, List<Post> postList);

    GetMyPageResponse toResponse(MyPageDto myPageDto);

    NicknameDto toDto(ChangeNicknameRequest changeNicknameRequest);
}
