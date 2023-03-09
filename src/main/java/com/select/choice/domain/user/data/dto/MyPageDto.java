package com.select.choice.domain.user.data.dto;

import com.select.choice.domain.post.presentation.data.dto.AllPostListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class MyPageDto {
    private final String nickname;
    private final List<AllPostListDto> postList;
}
