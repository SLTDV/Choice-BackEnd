package com.select.choice.domain.post.domain.data.response;

import com.select.choice.domain.comment.data.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailResponse {
    private String authorname;
    private String thumbnail;
    private String title;
    private String content;
    private String firstVotingOption;
    private String secondVotingOption;
    private Integer firstVotingCount;
    private Integer secondVotingCount;
    private List<Comment> comment;
}
