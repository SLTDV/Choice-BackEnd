package com.select.choice.infrastructure.discord.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class DiscordDto {
    private final String content;
}
