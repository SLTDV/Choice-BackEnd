package com.select.choice.domain.post.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class PostListDto {
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
