package com.select.choice.infrastructure.feign.client;

import com.select.choice.infrastructure.discord.dto.DiscordDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "discordFeignClient", url = "${discord.webhookUrl}")
public interface DiscordFeignClient {

    @PostMapping(headers = "Content-Type: application/x-www-form-urlencoded")
    void sendDiscord(@RequestBody DiscordDto discordDto);
}
