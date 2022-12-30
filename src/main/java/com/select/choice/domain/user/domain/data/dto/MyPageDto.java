package com.select.choice.domain.user.domain.data.dto;

import com.select.choice.domain.post.domain.data.dto.PostListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class MyPageDto {
    private final String nickname;
    private final List<PostListDto> postList;
}
