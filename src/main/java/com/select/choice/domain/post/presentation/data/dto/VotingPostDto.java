package com.select.choice.domain.post.presentation.data.dto;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.VotingPost;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class VotingPostDto {
    private final Post post;
    private final VotingPost votingPost;
}
