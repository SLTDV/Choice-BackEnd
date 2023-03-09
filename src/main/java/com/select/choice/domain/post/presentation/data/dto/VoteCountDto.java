package com.select.choice.domain.post.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class VoteCountDto {
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
}
