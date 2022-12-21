package com.select.choice.domain.post.domain.data.reponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class PostResponse {
    private String idx;
    private String title;
    private String thumbnail;
    private String content;
    private String firstVotingOption;
    private String secondVotingOption;
    private Integer firstVotingCount;
    private Integer secondVotingCount;
}
