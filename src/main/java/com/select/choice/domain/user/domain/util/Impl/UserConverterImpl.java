package com.select.choice.domain.user.domain.util.Impl;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.util.PostConverter;
import com.select.choice.domain.user.domain.data.dto.MyPageDto;
import com.select.choice.domain.user.domain.data.dto.NicknameDto;
import com.select.choice.domain.user.domain.data.entity.User;
import com.select.choice.domain.user.domain.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.domain.data.response.GetMyPageResponse;
import com.select.choice.domain.user.domain.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {
    private final PostConverter postConverter;

    @Override
    public MyPageDto toMyPageDto(User user, List<Post> postList) {
        String nickname = user.getNickname();
        List<PostDto> postDtoList = postConverter.toPostDto(postList);
        return MyPageDto.builder()
                .nickname(nickname)
                .postList(postDtoList)
                .build();
    }

    @Override
    public GetMyPageResponse toMyPageResponse(MyPageDto myPageDto) {
        String nickname = myPageDto.getNickname();
        List<PostResponse> postResponseList = postConverter.toResponse(myPageDto.getPostList());
        return GetMyPageResponse.builder()
                .nickname(nickname)
                .postList(postResponseList)
                .build();
    }

    @Override
    public NicknameDto toNicknameDto(ChangeNicknameRequest changeNicknameRequest) {
        String nickname = changeNicknameRequest.getNickname();
        return NicknameDto.builder()
                .nickname(nickname)
                .build();
    }
}
