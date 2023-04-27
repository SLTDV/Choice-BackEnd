package com.select.choice.domain.post.presentation.data.dto;

import com.select.choice.domain.post.domain.entity.PostVotingState;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@RequiredArgsConstructor
public class PostDto {
    private final Long idx;
    private final String firstImageUrl;
    private final String secondImageUrl;
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
    private final Optional<PostVotingState> votingState;
    private final Integer participants;
    private final Integer commentCount;
}
