package com.select.choice.domain.user.presentation.data.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProfileImageRequest {
    private String image;
}
