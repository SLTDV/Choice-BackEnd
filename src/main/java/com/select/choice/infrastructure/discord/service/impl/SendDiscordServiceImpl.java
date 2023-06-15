package com.select.choice.infrastructure.discord.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.infrastructure.discord.dto.DiscordDto;
import com.select.choice.infrastructure.discord.service.SendDiscordService;
import com.select.choice.infrastructure.feign.client.DiscordFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendDiscordServiceImpl implements SendDiscordService {
    private final DiscordFeignClient discordFeignClient;

    @Override
    public void sendMessage(Post post) {
        String content = "";

        content = "@everyone \n";
        content += "게시물 " + "idx: " + post.getIdx() + "\n";
        content += "위의 idx 게시물이 10회 이상 신고가 누적되었습니다.\n";

        discordFeignClient.sendDiscord(new DiscordDto(content));
    }
}
