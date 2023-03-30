package com.select.choice.domain.auth.presentation.data.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDuplicationCheckRequest {
    private String email;
}
