package com.select.choice.domain.post.domain.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CreatePostRequestDto {
    private String title;
    private String content;
    private String thumbnail;
    private String firstVotingOption;
    private String secondVotingOtion;
}
