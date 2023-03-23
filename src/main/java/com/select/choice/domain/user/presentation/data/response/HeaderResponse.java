package com.select.choice.domain.user.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Builder
@RequiredArgsConstructor
public class HeaderResponse {
    private final String nickname;
    private final String image;
}
