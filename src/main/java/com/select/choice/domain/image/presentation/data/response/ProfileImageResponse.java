package com.select.choice.domain.image.presentation.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ProfileImageResponse {
    private final String profileImageUrl;
}
