package com.select.choice.domain.post.presentation;

import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.response.VoteCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;

import com.select.choice.domain.post.presentation.data.response.PostResponse;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.presentation.data.response.WebVerPostResponse;
import com.select.choice.domain.post.service.PostService;
import com.select.choice.domain.post.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<PostResponse>> getAllPostList(){
        List<PostDto> postList = postService.getAllPostList();
        List<PostResponse> postResponses = postConverter.toResponse(postList);
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    /*
    기능: 게시물 조회 WEB .ver
    담당자: 노혁
     */
    @GetMapping("/web")
    public ResponseEntity<List<WebVerPostResponse>> getPost(){
        List<WebVerPostDto> webVerPostDtoList = postService.getPost();
        List<WebVerPostResponse> webVerPostResponseList = postConverter.toPostResponse(webVerPostDtoList);
        return new ResponseEntity<>(webVerPostResponseList, HttpStatus.OK);
    }

    /*
    기능: 인기 게시물 조회
    담당자: 진시윤
     */
    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>> getBestPostList(){
        List<PostDto> bestPostList = postService.getBestPostList();

        List<PostResponse> bestPostResponseList = postConverter.toBesetPostResponse(bestPostList);
        return new ResponseEntity<>(bestPostResponseList, HttpStatus.OK);
    }

    /*
    기능: 인기 게시물 조회 WEB .ver
    담당자: 노혁
     */
    @GetMapping("/list/web")
    public ResponseEntity<List<WebVerPostResponse>> getBestPost() {
        List<WebVerPostDto> bestPostList = postService.getBestPost();
        List<WebVerPostResponse> bestPostResponseList = postConverter.toBesetPostDtoResponse(bestPostList);
        return new ResponseEntity<>(bestPostResponseList, HttpStatus.OK);
    }

    /*
    기능: 게시물 생성
    담당자: 진시윤
     */
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequestDto) {
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
        PostDetailDto dto = postService.aggregateDetail(postIdx);
        PostDetailResponse response = postConverter.toResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    기능: 투표 수 더하기
    담당자: 노혁
     */
    @PostMapping("/vote/{postIdx}")
    public ResponseEntity<VoteCountResponse> voteCount(@PathVariable("postIdx") Long postIdx, @RequestBody AddCountRequest addCountRequest){
        AddCountDto addCountDto = postConverter.toDto(addCountRequest);
        VoteCountDto voteCountDto = postService.voteCount(addCountDto, postIdx);
        VoteCountResponse addCountResponse = postConverter.toResponse(voteCountDto);
        return new ResponseEntity<>(addCountResponse, HttpStatus.CREATED);
    }
}