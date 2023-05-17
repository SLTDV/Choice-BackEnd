package com.select.choice.domain.user.util.Impl;

import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.dto.WebPostDto;
import com.select.choice.domain.post.presentation.data.response.PostResponse;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.presentation.data.dto.*;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.presentation.data.request.ChangeProfileImageRequest;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;
import com.select.choice.domain.user.presentation.data.response.HeaderResponse;
import com.select.choice.domain.user.presentation.data.response.WebMyPageResponse;
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
        String image = user.getProfileImageUrl();
        List<PostDto> postDtoList = postConverter.toDto(postList);
        return MyPageDto.builder()
                .nickname(nickname)
                .image(image)
                .postList(postDtoList)
                .build();
    }

    @Override
    public GetMyPageResponse toResponse(MyPageDto myPageDto) {
        String nickname = myPageDto.getNickname();
        String image = myPageDto.getImage();
        List<PostResponse> postResponseList = postConverter.toResponse(myPageDto.getPostList());
        return GetMyPageResponse.builder()
                .nickname(nickname)
                .image(image)
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

    @Override
    public ChangeProfileImageDto toDto(ChangeProfileImageRequest changeProfileImageRequest) {
        String image = changeProfileImageRequest.getImage();
        return ChangeProfileImageDto.builder()
                .image(image)
                .build();
    }

    @Override
    public HeaderDto toDto(User user) {
        return HeaderDto.builder()
                .nickname(user.getNickname())
                .image(user.getProfileImageUrl())
                .build();
    }

    @Override
    public HeaderResponse toResponse(HeaderDto headerDto) {
        return HeaderResponse.builder()
                .nickname(headerDto.getNickname())
                .image(headerDto.getImage())
                .build();
    }

    @Override
    public WebMyPageDto toWebMyPageDto(User user, List<WebMyPagePostDto> postDtoList) {
        return WebMyPageDto.builder()
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .postList(postDtoList)
                .build();
    }

    @Override
    public WebMyPageResponse toResponse(WebMyPageDto myPageDto) {
        return WebMyPageResponse.builder()
                .nickname(myPageDto.getNickname())
                .profileImageUrl(myPageDto.getProfileImageUrl())
                .postList(postConverter.toWebMyPagePostResponse(myPageDto.getPostList()))
                .build();
    }
}
