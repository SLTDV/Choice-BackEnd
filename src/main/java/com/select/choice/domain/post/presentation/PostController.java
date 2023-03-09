package com.select.choice.domain.post.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.select.choice.domain.post.presentation.data.dto.AddCountDto;
import com.select.choice.domain.post.presentation.data.dto.CreatePostDto;
import com.select.choice.domain.post.presentation.data.dto.AllPostListDto;
import com.select.choice.domain.post.presentation.data.response.AddCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;

import com.select.choice.domain.post.presentation.data.response.AllPostListResponse;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.service.PostService;
import com.select.choice.domain.post.util.PostConverter;
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
    public ResponseEntity<List<AllPostListResponse>> getAllPostList(){
        List<AllPostListDto> allPostList = postService.getAllPostList();
        List<AllPostListResponse> postListResponseList = postConverter.toResponse(allPostList);
        return new ResponseEntity<>(postListResponseList, HttpStatus.OK);
    }

    /*
    기능: 인기 게시물 조회
    담당자: 진시윤
     */
    @GetMapping("/list")
    public ResponseEntity<List<AllPostListResponse>> getBestPostList(){
        List<AllPostListResponse> response = postService.getBestPostList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    기능: 게시물 생성
    담당자: 진시윤
     */
    @PostMapping
    @JsonProperty("CreatePostRequestDto")
    public ResponseEntity<Void> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        CreatePostDto dto = postConverter.toDto(createPostRequestDto);
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 게시물 상세페이지 조회
    담당자: 진시윤
     */
    @GetMapping({"/{postIdx}"})
    public ResponseEntity<PostDetailResponse> postDetail(@PathVariable("postIdx") Long postIdx) {
        PostDetailResponse response = postService.aggregateDetail(postIdx);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    기능: 투표 수 더하기
    담당자: 노혁
     */
    @PostMapping("/add/{postIdx}")
    public ResponseEntity<AddCountResponse> addCount(@PathVariable("postIdx") Long postIdx, @RequestBody AddCountRequest addCountRequest){
        AddCountDto addCountDto = postConverter.toDto(addCountRequest);
         AddCountResponse response = postService.addCount(addCountDto, postIdx);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}