package com.select.choice.domain.post.domain.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PostDto {
    private Long idx;
    private String thumbnail;
    private String title;
    private String content;
    private String firstVotingOption;
    private String secondVotingOption;
    private Integer firstVotingCount;
    private Integer secondVotingCount;
}
