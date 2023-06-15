package com.select.choice.infrastructure.discord.service;

import com.select.choice.domain.post.domain.entity.Post;

public interface SendDiscordService {
    void sendMessage(Post post);
}
