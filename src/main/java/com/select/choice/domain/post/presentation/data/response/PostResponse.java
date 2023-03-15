package com.select.choice.domain.post.presentation.data.response;

import com.select.choice.domain.post.domain.entity.VotingPost;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@Builder
@RequiredArgsConstructor
public class PostResponse {
    private final Long idx;
    private final String firstImageUrl;
    private final String secondImageUrl;
    private final String title;
    private final String content;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
    private final List<VotingPost> voting;
    private final int participants;
    private final int commentCount;
}
