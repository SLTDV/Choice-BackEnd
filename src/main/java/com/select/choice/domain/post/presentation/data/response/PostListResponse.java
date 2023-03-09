package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Builder
public class PostListResponse {
    private final Long idx;
    private final String thumbnail;
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
    private final boolean IsVoting;
}
