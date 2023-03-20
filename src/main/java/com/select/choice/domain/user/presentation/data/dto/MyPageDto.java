package com.select.choice.domain.user.presentation.data.dto;

import com.select.choice.domain.post.presentation.data.dto.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class MyPageDto {
    private final String nickname;
    private final String image;
    private final List<PostDto> postList;
}
