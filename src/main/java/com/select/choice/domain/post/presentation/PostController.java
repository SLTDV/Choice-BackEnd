package com.select.choice.domain.post.presentation;

import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.response.*;
import com.select.choice.domain.post.presentation.data.request.VoteOptionRequest;

import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.service.*;
import com.select.choice.domain.post.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final CreatePostService createPostService;
    private final DeletePostService deletePostService;
    private final GetLatestPostsService getLatestPostsService;
    private final GetPostDetailService getPostDetailService;
    private final GetTopFivePostsByVoteCountTodayService getTopFivePostsByVoteCountTodayService;
    private final VoteForPostService voteForPostService;
    private final GetPopularPostsService getPopularPostsService;
    private final PostConverter postConverter;

    /*
    기능: 게시물 조회
    담당자: 진시윤, 노혁
     */
    @GetMapping
    public ResponseEntity<PostListResponse> getAllPostList(Pageable pageable){
        List<PostDto> postList = getLatestPostsService.getLatestPost(pageable);
        List<PostResponse> postResponses = postConverter.toResponse(postList);
        return new ResponseEntity<>(postConverter.toResponse(postResponses, pageable.getPageNumber()), HttpStatus.OK);
    }

    /*
    기능: 게시물 조회 WEB.ver
    담당자: 노혁
     */
    @GetMapping("/latested")
    public ResponseEntity<WebPostListResponse> getPost(Pageable pageable){
        List<WebPostDto> webVerPostDtoList = getLatestPostsService.getLatestPostList(pageable);
        List<WebPostResponse> webVerPostResponseList = postConverter.toPostResponse(webVerPostDtoList);
        return new ResponseEntity<>(postConverter.toWebResponse(webVerPostResponseList, pageable.getPageNumber()), HttpStatus.OK);
    }

    /*
    기능: 인기 게시물 조회
    담당자: 진시윤, 노혁
     */
    @GetMapping("/list")
    public ResponseEntity<PostListResponse> getBestPostList(Pageable pageable){
        List<PostDto> bestPostList = getPopularPostsService.getPopularPosts(pageable);
        List<PostResponse> bestPostResponseList = postConverter.toResponse(bestPostList);
        return new ResponseEntity<>(postConverter.toResponse(bestPostResponseList, pageable.getPageNumber()), HttpStatus.OK);
    }

    /*
    기능: 인기 게시물 조회 WEB.ver
    담당자: 노혁
     */
    @GetMapping("/popularity")
    public ResponseEntity<WebPostListResponse> getBestPost(Pageable pageable) {
        List<WebPostDto> bestPostList = getPopularPostsService.getPopularPostList(pageable);
        List<WebPostResponse> bestPostResponseList = postConverter.toPostResponse(bestPostList);
        return new ResponseEntity<>(postConverter.toWebResponse(bestPostResponseList, pageable.getPageNumber()), HttpStatus.OK);
    }

    /*
    기능: 게시물 생성
    담당자: 진시윤, 노혁
     */
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequestDto) {
        CreatePostDto dto = postConverter.toDto(createPostRequestDto);
        createPostService.createPost(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    기능: 게시물 삭제
    담당자: 진시윤
     */
    @DeleteMapping("/{postIdx}")
    public ResponseEntity<Void> deletePost(@PathVariable("postIdx") Long postIdx){
        deletePostService.deletePost(postIdx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 게시물 상세페이지 조회
    담당자: 노혁, 진시윤
     */
    @GetMapping({"/{postIdx}"})
    public ResponseEntity<PostDetailResponse> postDetail(@PathVariable("postIdx") Long postIdx, Pageable pageable) {
        PostDetailDto dto = getPostDetailService.getPostDetail(postIdx, pageable);
        PostDetailResponse response = postConverter.toResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    기능: 게시물 상세페이지 조회 WEb.ver
    담당자: 노혁
     */
    @GetMapping("/detail/{postIdx}")
    public ResponseEntity<WebPostDetailResponse> getPostDetail(@PathVariable("postIdx") Long postIdx, Pageable pageable) {
        WebPostDetailDto dto = getPostDetailService.getWebPostDetail(postIdx, pageable);
        WebPostDetailResponse response = postConverter.toResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    기능: 투표 수 더하기
    담당자: 노혁
     */
    @PostMapping("/vote/{postIdx}")
    public ResponseEntity<VoteForPostResponse> voteCount(@PathVariable("postIdx") Long postIdx, @RequestBody VoteOptionRequest addCountRequest){
        VoteOptionDto addCountDto = postConverter.toDto(addCountRequest);
        VoteForPostDto voteForPostDto = voteForPostService.voteForPost(addCountDto, postIdx);
        VoteForPostResponse addCountResponse = postConverter.toResponse(voteForPostDto);
        return new ResponseEntity<>(addCountResponse, HttpStatus.CREATED);
    }

    /*
    기능: 오늘의 choice
    담당자: 노혁
     */
    @GetMapping("/today")
    public ResponseEntity<TodayPostListResponse> getTodayPostList() {
        TodayPostListDto todayPostListDto = getTopFivePostsByVoteCountTodayService.getTopFivePostsByVoteCountTodayService();
        List<TodayPostResponse> todayPostListResponses = todayPostListDto.getTodayPostList().stream()
                .limit(5)
                .map(postConverter::toTodayPostResponse)
                .collect(Collectors.toList());

        TodayPostListResponse todayPostListResponse = postConverter.toTodayPostListResponse(todayPostListResponses);
        return new ResponseEntity<>(todayPostListResponse, HttpStatus.OK);
    }
}