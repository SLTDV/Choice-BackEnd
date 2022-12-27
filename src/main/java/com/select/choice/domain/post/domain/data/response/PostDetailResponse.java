package com.select.choice.domain.post.domain.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder

public class PostDetailResponse {
    private final Long idx;
    private final String thumbnail;
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
    private final String comment;
    private final String nickname;

}
