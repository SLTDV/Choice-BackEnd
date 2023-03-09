package com.select.choice.domain.post.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
@Builder
public class AddCountResponse {
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
}
