package com.select.choice.domain.post.domain.service;


import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDetailDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<PostDto> getAllPostList();
    List<PostDto> getBestPostList();
    void createPost(CreatePostDto createPostDto);
    PostDetailResponse aggregateDetail(Long postIdx);

    void deletePost(Long postIdx);
}
