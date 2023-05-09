package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class VoteForPostResponse {
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
}
