
package com.select.choice.domain.post.domain.data.dto;

import com.select.choice.domain.comment.domain.data.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailDto {
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