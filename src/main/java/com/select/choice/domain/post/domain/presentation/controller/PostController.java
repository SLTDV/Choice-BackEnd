package com.select.choice.domain.post.domain.presentation.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostConverter postConverter;
    //게시물 조회
    @GetMapping()
    public ResponseEntity<List<PostResponse>>getAllPostList(){
        List<PostDto> dto = postService.getAllPostList();
        List<PostResponse> body = postConverter.toResponse(dto);
        return ResponseEntity.ok(body);
    }
    //인기 게시물 조회
    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>>getBestPostList(){
        List<PostDto> dto = postService.getBestPostList();
        List<PostResponse> body = postConverter.toResponse(dto);
        return ResponseEntity.ok(body);
    }
    //게시물 생성
    @PostMapping()
    @JsonProperty("CreatePostRequestDto")
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        CreatePostDto dto = postConverter.toCreatePost(createPostRequestDto);
        postService.createPost(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //게시물 삭제
    @DeleteMapping("/{postIdx}")
    public ResponseEntity<Void> deletePost(@PathVariable("postIdx") Long postIdx){
        postService.deletePost(postIdx);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //게시물 상세페이지 조회
    @GetMapping({"/postIdx"})
    public ResponseEntity<PostDetailResponse>postDetail(Long idx, Model model) {
        List<>
        model.addAttribute()
    }

}