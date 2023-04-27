package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class WebPostResponse {
    private final Long idx;
    private final String imageUrl;
    private final String title;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final int participants;
    private final int commentCount;
}
