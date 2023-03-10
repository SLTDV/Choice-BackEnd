package com.select.choice.domain.user.presentation.data.dto;

import com.select.choice.domain.post.presentation.data.dto.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class MyPageDto {
    private final String nickname;
    private final List<PostDto> postList;
}
