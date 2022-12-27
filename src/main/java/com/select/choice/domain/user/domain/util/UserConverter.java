package com.select.choice.domain.user.domain.util;

import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.domain.data.dto.MyPageDto;
import com.select.choice.domain.user.domain.data.entity.User;
import com.select.choice.domain.user.domain.data.response.GetMyPageResponse;

import java.util.List;

public interface UserConverter {
    MyPageDto toMyPageDto(User user, List<Post> postList);

    GetMyPageResponse toMyPageResponse(MyPageDto myPageDto);
}
