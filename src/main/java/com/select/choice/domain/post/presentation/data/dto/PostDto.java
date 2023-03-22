package com.select.choice.domain.post.presentation.data.dto;

import com.select.choice.domain.post.domain.entity.PostVotingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    private final int firstVotingCount;
    private final int secondVotingCount;
    private final List<PostVotingStatus> votingPost;
    private final int participants;
    private final int commentCount;
}
