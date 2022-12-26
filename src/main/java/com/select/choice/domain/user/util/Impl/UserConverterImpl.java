package com.select.choice.domain.user.util.Impl;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.util.PostConverter;
import com.select.choice.domain.user.data.dto.MyPageDto;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.data.response.GetMyPageResponse;
import com.select.choice.domain.user.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {
    private final PostConverter postConverter;

    @Override
    public MyPageDto toMyPageDto(User user, List<Post> postList) {
        String username = user.getNickname();
        List<PostDto> postDtoList = postConverter.toPostDto(postList);
        return MyPageDto.builder()
                .username(username)
                .postList(postDtoList)
                .build();
    }

    @Override
    public GetMyPageResponse toMyPageResponse(MyPageDto myPageDto) {
        String username = myPageDto.getUsername();
        List<PostResponse> postResponseList = postConverter.toResponse(myPageDto.getPostList());
        return GetMyPageResponse.builder()
                .username(username)
                .postList(postResponseList)
                .build();
    }
}
