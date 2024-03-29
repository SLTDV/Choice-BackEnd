package com.select.choice.domain.user.presentation.data.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class WebMyPagePostResponse {
    private final Long idx;
    private final String title;
    private final String imageUrl;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer participants;
    private final Integer commentCount;
}
