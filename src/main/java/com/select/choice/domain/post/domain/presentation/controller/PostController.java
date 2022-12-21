package com.select.choice.domain.post.domain.presentation.controller;

import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;
    private PostConverter postConverter;

    public ResponseEntity<List<PostResponse>>getAllPostList(){
        List<PostDto> dto = postService.getAllPostList();
        return postConverter.toResponse(dto);
    }
}