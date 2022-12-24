package com.select.choice.domain.comment.data.request;

import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriteCommentRequest {
    private String content;
}
