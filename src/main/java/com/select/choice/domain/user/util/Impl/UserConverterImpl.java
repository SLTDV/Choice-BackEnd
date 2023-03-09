package com.select.choice.domain.user.util.Impl;

import com.select.choice.domain.post.presentation.data.dto.AllPostListDto;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.response.AllPostListResponse;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.data.dto.MyPageDto;
import com.select.choice.domain.user.data.dto.NicknameDto;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.data.request.ChangeNicknameRequest;
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
    public MyPageDto toDto(User user, List<Post> postList) {
        String nickname = user.getNickname();
        List<AllPostListDto> postDtoList = postConverter.toPostListDto(postList);
        return MyPageDto.builder()
                .nickname(nickname)
                .postList(postDtoList)
                .build();
    }

    @Override
    public GetMyPageResponse toResponse(MyPageDto myPageDto) {
        String nickname = myPageDto.getNickname();
        List<AllPostListResponse> postResponseList = postConverter.toResponse(myPageDto.getPostList());
        return GetMyPageResponse.builder()
                .nickname(nickname)
                .postList(postResponseList)
                .build();
    }

    @Override
    public NicknameDto toDto(ChangeNicknameRequest changeNicknameRequest) {
        String nickname = changeNicknameRequest.getNickname();
        return NicknameDto.builder()
                .nickname(nickname)
                .build();
    }
}
