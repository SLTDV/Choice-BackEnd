package com.select.choice.domain.user.presentation.data.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProfileImageRequest {
    private String image;
}
