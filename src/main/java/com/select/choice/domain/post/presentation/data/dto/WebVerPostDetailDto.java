package com.select.choice.domain.post.presentation.data.dto;

import com.select.choice.domain.post.domain.entity.PostVotingState;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@Builder
@RequiredArgsConstructor
public class WebVerPostDetailDto {
    private final String title;
    private final String content;
    private final String firstImageUrl;
    private final String secondImageUrl;
    private final String firstVotingOption;
    private final String secondVotingOption;
    private final Integer firstVotingCount;
    private final Integer secondVotingCount;
    private final String writer;
    private final String profileImageUrl;
    private final Integer page;
    private final Integer size;
    private final Optional<PostVotingState> votingState;
    private final List<CommentDetailDto> comment;
}
