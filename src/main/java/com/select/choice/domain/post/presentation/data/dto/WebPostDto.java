package com.select.choice.domain.post.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class WebPostDto {
    private final Long idx;
    private final String imageUrl;
    private final String title;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer participants;
    private final Integer commentCount;
}
