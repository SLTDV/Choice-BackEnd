package com.select.choice.domain.post.service;

import com.select.choice.domain.post.presentation.data.dto.VoteOptionDto;
import com.select.choice.domain.post.presentation.data.dto.VoteForPostDto;

public interface VoteForPostService {
    VoteForPostDto voteForPost(VoteOptionDto addCountDto, Long postIdx);
}
