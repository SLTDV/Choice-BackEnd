package com.select.choice.domain.post.domain.data.request;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestDto {
    private String title;
    private String content;
    private String firstVotingOption;
    private String secondVotingOption;
    private String thumbnail;
}