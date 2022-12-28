package com.select.choice.domain.post.domain.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class AddCountDto {
    private final Integer choice;
}
