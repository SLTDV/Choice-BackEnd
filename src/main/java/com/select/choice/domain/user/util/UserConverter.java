package com.select.choice.domain.user.util;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.user.presentation.data.dto.*;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.presentation.data.request.ChangeProfileImageRequest;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;
import com.select.choice.domain.user.presentation.data.response.HeaderResponse;
import com.select.choice.domain.user.presentation.data.response.WebMyPageResponse;

import java.util.List;

public interface UserConverter {
    MyPageDto toDto(User user, List<Post> postList);
    GetMyPageResponse toResponse(MyPageDto myPageDto);
    NicknameDto toDto(ChangeNicknameRequest changeNicknameRequest);
    ChangeProfileImageDto toDto(ChangeProfileImageRequest changeProfileImageRequest);
    HeaderDto toDto(User user);
    HeaderResponse toResponse(HeaderDto headerDto);
    WebMyPageDto toWebMyPageDto(User user, List<WebPostDto> postDtoList);
    WebMyPageResponse toResponse(WebMyPageDto myPageDto);
}
