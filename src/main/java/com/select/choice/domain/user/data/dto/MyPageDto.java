package com.select.choice.domain.user.data.dto;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class MyPageDto {
    private final String username;
    private final List<PostDto> postList;
}