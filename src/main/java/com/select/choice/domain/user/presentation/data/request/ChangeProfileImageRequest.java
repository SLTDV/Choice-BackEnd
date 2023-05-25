package com.select.choice.domain.user.presentation.data.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProfileImageRequest {
    @Size(min = 2, max = 10, message = "닉네임은 2글자 이상 10글자 이하입니다.")
    private String image;
}
