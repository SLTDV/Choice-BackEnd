package com.select.choice.domain.post.domain.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    public ResponseEntity<PostListResponse>getAllPostList(
            @RequestBody GetAllPostListRequest request){

    }
}