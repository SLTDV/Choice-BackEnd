package com.select.choice.domain.post.domain.service;


import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<PostDto> getAllPostList();
    List<PostDto> getBestPostList();
    void createPost(CreatePostDto createPostDto, MultipartFile image) throws IOException;

    void deletePost(Long postIdx);
}
