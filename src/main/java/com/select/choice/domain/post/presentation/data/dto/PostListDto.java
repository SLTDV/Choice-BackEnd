package com.select.choice.domain.post.presentation.data.dto;

import com.select.choice.domain.post.domain.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostListDto {
    private final List<Post> posts;
    private final Pageable pageable;
}
