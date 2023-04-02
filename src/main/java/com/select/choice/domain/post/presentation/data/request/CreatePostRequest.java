package com.select.choice.domain.post.presentation.data.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class CreatePostRequest {
    @Size(min = 2, max = 16)
    private final String title;
    @Size(min = 2, max = 100)
    private final String content;
    @Size(min = 2, max = 8)
    private final String firstVotingOption;
    @Size(min = 2, max = 8)
    private final String secondVotingOption;
    private final String firstImageUrl;
    private final String secondImageUrl;
}