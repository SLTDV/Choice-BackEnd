package com.select.choice.domain.post.presentation.data.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class CreatePostRequestDto {
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final String firstImageUrl;
    private final String secondImageUrl;
}