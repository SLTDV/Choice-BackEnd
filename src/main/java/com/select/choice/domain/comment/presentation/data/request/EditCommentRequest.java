package com.select.choice.domain.comment.presentation.data.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class EditCommentRequest {
    private final String content;
}
