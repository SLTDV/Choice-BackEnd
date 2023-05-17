package com.select.choice.domain.post.presentation.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class TotalPageAndWebPostDtoList {
    private final Integer totalPage;
    private final List<WebPostDto> webPostDtoList;
}
