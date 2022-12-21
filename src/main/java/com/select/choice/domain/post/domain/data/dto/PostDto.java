package com.select.choice.domain.post.domain.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostDto {
    private String idx;
    private String title;
    private String thumbnail;
    private String content;
    private String firstVotingOption;
    private String secondVotingOption;
    private Integer firstVotingCount;
    private Integer secondVotingCount;
}
