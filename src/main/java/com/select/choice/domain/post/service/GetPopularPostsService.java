package com.select.choice.domain.post.service;

import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.TotalPageAndWebPostDtoList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetPopularPostsService {
    List<PostDto> getPopularPosts(Pageable pageable);
    TotalPageAndWebPostDtoList getPopularPostList(Pageable pageable);
}
