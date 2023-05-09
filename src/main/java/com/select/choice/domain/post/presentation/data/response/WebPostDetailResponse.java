package com.select.choice.domain.post.presentation.data.response;

import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WebPostDetailResponse {
    private final Integer page;
    private final Integer size;
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
    private final Integer votingState;
    private final List<CommentDetailDto> comment;
}
