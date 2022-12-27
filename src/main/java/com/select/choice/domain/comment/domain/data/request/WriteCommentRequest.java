package com.select.choice.domain.comment.domain.data.request;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriteCommentRequest {
    private String content;
}
