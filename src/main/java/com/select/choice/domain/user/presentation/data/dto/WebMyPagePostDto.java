package com.select.choice.domain.user.presentation.data.dto;

import com.select.choice.domain.post.domain.entity.PostVotingState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class WebMyPagePostDto {
    private final Long idx;
    private final String title;
    private final String imageUrl;
    private final String firstVotingOption;
    private final Optional<PostVotingState> votingState;
    private final Integer participants;
    private final Integer commentCount;
}
