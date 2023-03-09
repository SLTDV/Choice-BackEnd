package com.select.choice.domain.post.presentation.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreatePostDto {
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final String firstImageUrl;
    private final String secondImageUrl;
}
