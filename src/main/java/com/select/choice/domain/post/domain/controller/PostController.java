package com.select.choice.domain.post.domain.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.select.choice.domain.post.domain.data.dto.AddCountDto;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.request.AddCountRequest;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @PostMapping()
    @JsonProperty("CreatePostRequestDto")
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        CreatePostDto dto = postConverter.toCreatePost(createPostRequestDto);
        postService.createPost(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{postIdx}")
    public ResponseEntity<Void> deletePost(@PathVariable("postIdx") Long postIdx){
        postService.deletePost(postIdx);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/add/{postIdx}")
    public ResponseEntity<Void> addCount(@PathVariable("postIdx") Long postIdx, @RequestBody AddCountRequest addCountRequest){
        AddCountDto addCountDto = postConverter.toAddCountDto(addCountRequest);
        postService.addCount(addCountDto, postIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}