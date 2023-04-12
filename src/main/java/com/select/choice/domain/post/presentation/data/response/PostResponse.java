package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class PostResponse {
    private final Long idx;
    private final String firstImageUrl;
    private final String secondImageUrl;
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final int firstVotingCount;
    private final int secondVotingCount;
    private final int votingState;
    private final int participants;
    private final int commentCount;
}
