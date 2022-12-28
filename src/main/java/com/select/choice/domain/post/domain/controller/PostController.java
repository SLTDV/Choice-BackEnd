package com.select.choice.domain.post.domain.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.select.choice.domain.post.domain.data.dto.AddCountDto;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDetailDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.dto.AddCountDto;
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

    /*
    기능: 게시물 조회
    담당자: 진시윤
     */
    @GetMapping
    public ResponseEntity<List<PostResponse>>getAllPostList(){
        List<PostDto> dto = postService.getAllPostList();
        List<PostResponse> body = postConverter.toResponse(dto);
        return ResponseEntity.ok(body);
    }

    /*
    기능: 인기 게시물 조회
    담당자: 진시윤
     */
    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>>getBestPostList(){
        List<PostDto> dto = postService.getBestPostList();
        List<PostResponse> body = postConverter.toResponse(dto);
        return ResponseEntity.ok(body);
    }

    /*
    기능: 게시물 생성
    담당자: 진시윤
     */
    @PostMapping
    @JsonProperty("CreatePostRequestDto")
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        CreatePostDto dto = postConverter.toCreatePost(createPostRequestDto);
        postService.createPost(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    기능: 게시물 삭제
    담당자: 진시윤
     */
    @DeleteMapping("/{postIdx}")
    public ResponseEntity<Void> deletePost(@PathVariable("postIdx") Long postIdx){
        postService.deletePost(postIdx);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*
    기능: 게시물 상세페이지 조회
    담당자: 진시윤
     */
    @GetMapping({"/{postIdx}"})
    public ResponseEntity<PostDetailResponse>postDetail(@PathVariable("postIdx")Long postIdx) {
        PostDetailResponse postDetailResponse = postService.aggregateDetail(postIdx);
        return ResponseEntity.status(HttpStatus.OK).body(postDetailResponse);
    }

    /*
    기능: 투표 수 더하기
    담당자: 노혁
     */
    @PostMapping("/add/{postIdx}")
    public ResponseEntity<Void> addCount(@PathVariable("postIdx") Long postIdx, @RequestBody AddCountRequest addCountRequest){
        AddCountDto addCountDto = postConverter.toAddCountDto(addCountRequest);
        postService.addCount(addCountDto, postIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}