package com.select.choice.domain.post.domain.presentation.controller;

import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping()
    public ResponseEntity<Void> createPost(
            @RequestPart(value = "file", required = false)MultipartFile image,
            @RequestPart(value = "req") CreatePostRequestDto createPostRequestDto) throws Exception{
        CreatePostDto dto = postConverter.toCreatePost(createPostRequestDto);
        postService.createPost(dto,image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{post-idx}")
    public ResponseEntity<Void>deletePost(Long postIdx){
        postService.deletePost(postIdx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}