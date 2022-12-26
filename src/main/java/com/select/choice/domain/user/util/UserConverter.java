package com.select.choice.domain.user.util;

import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.data.dto.MyPageDto;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.data.response.GetMyPageResponse;

import java.util.List;

public interface UserConverter {
    MyPageDto toMyPageDto(User user, List<Post> postList);

    GetMyPageResponse toMyPageResponse(MyPageDto myPageDto);
}
