package com.select.choice.domain.post.domain.presentation.controller;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import com.select.choice.domain.post.domain.request.CreatePostRequestDto;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostConverter postConverter;
    @GetMapping()
    public ResponseEntity<List<PostResponse>>getAllPostList(){
        List<PostDto> dto = postService.getAllPostList();
        List<PostResponse> body = postConverter.toResponse(dto);
        return ResponseEntity.ok(body);
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>>getBestPostList(){
        List<PostDto> dto = postService.getBestPostList();
        List<PostResponse> body = postConverter.toResponse(dto);
        return ResponseEntity.ok(body);
    }
    /*@PostMapping()
    public RequestEntity<List<CreatePostRequestDto>>*/
}