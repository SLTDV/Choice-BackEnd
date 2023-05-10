package com.select.choice.domain.post.util.Impl;

import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.post.domain.repository.PostVotingStateRepository;
import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.entity.PostVotingState;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.presentation.data.response.*;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.request.VoteOptionRequest;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.presentation.data.dto.WebMyPagePostDto;
import com.select.choice.domain.user.presentation.data.response.WebMyPagePostResponse;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverterImpl implements PostConverter {
    private final UserUtil userUtil;
    private final CommentRepository commentRepository;
    private final PostVotingStateRepository postVotingStateRepository;

    @Override
    public List<PostResponse> toResponse(List<PostDto> dto) {
        return dto.stream().map(post -> {
            if(post.getVotingState().isPresent()){
                return new PostResponse(
                        post.getIdx(),
                        post.getFirstImageUrl(),
                        post.getSecondImageUrl(),
                        post.getTitle(),
                        post.getContent(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount(),
                        post.getVotingState().get().getVote(),
                        post.getParticipants(),
                        post.getCommentCount()
                );
            } else {
                return new PostResponse(
                        post.getIdx(),
                        post.getFirstImageUrl(),
                        post.getSecondImageUrl(),
                        post.getTitle(),
                        post.getContent(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount(),
                        0,
                        post.getParticipants(),
                        post.getCommentCount()
                );
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> toDto(List<Post> entity) {
        User user = userUtil.currentUser();
        return entity.stream().map(post ->
                new PostDto(
                        post.getIdx(),
                        post.getFirstImageUrl(),
                        post.getSecondImageUrl(),
                        post.getTitle(),
                        post.getContent(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount(),
                        postVotingStateRepository.findByUserAndPost(user, post),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        commentRepository.countByPost(post)
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<WebPostDto> toPostDto(List<Post> postList) {
        return postList.stream().map(post ->
                new WebPostDto(
                        post.getIdx(),
                        post.getFirstImageUrl(),
                        post.getTitle(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        commentRepository.countByPost(post)
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<WebPostResponse> toPostResponse(List<WebPostDto> webVerPostDtoList) {
        return webVerPostDtoList.stream().map(dto ->
                new WebPostResponse(
                        dto.getIdx(),
                        dto.getImageUrl(),
                        dto.getTitle(),
                        dto.getFirstVotingOption(),
                        dto.getSecondVotingOption(),
                        dto.getParticipants(),
                        dto.getCommentCount()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<WebPostDto> toBestPostDtoList(List<Post> list) {
        return list.stream().map(entity ->
                new WebPostDto(
                        entity.getIdx(),
                        entity.getFirstImageUrl(),
                        entity.getTitle(),
                        entity.getFirstVotingOption(),
                        entity.getSecondVotingOption(),
                        entity.getFirstVotingCount() + entity.getSecondVotingCount(),
                        commentRepository.countByPost(entity)
                )
        ).collect(Collectors.toList());
    }

    @Override
    public TodayPostDto toTodayPostDto(Post post) {
        return TodayPostDto.builder()
                .idx(post.getIdx())
                .title(post.getTitle())
                .firstVotingOption(post.getFirstVotingOption())
                .secondVotingOption(post.getSecondVotingOption())
                .imageUrl(post.getFirstImageUrl())
                .participants(post.getFirstVotingCount() + post.getSecondVotingCount())
                .commentCount(commentRepository.countByPost(post))
                .build();
    }

    @Override
    public TodayPostListDto toTodayPostListDto(List<TodayPostDto> todayPostList) {
        return TodayPostListDto.builder()
                .todayPostList(todayPostList)
                .build();
    }

    @Override
    public TodayPostResponse toTodayPostResponse(TodayPostDto todayPostDto) {
        return TodayPostResponse.builder()
                .idx(todayPostDto.getIdx())
                .title(todayPostDto.getTitle())
                .firstVotingOption(todayPostDto.getFirstVotingOption())
                .secondVotingOption(todayPostDto.getSecondVotingOption())
                .commentCount(todayPostDto.getCommentCount())
                .participants(todayPostDto.getParticipants())
                .imageUrl(todayPostDto.getImageUrl())
                .build();
    }

    @Override
    public TodayPostListResponse toTodayPostListResponse(List<TodayPostResponse> todayPostListResponses) {
        return TodayPostListResponse.builder()
                .todayPostList(todayPostListResponses)
                .build();
    }

    @Override
    public WebPostDetailDto toPostDetailDto(List<CommentDetailDto> commentDetailDtoList, Post post, Pageable pageable, User user) {
        return WebPostDetailDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .firstImageUrl(post.getFirstImageUrl())
                .secondImageUrl(post.getSecondImageUrl())
                .profileImageUrl(post.getUser().getProfileImageUrl())
                .firstVotingOption(post.getFirstVotingOption())
                .secondVotingOption(post.getSecondVotingOption())
                .firstVotingCount(post.getFirstVotingCount())
                .secondVotingCount(post.getSecondVotingCount())
                .writer(post.getUser().getNickname())
                .page(pageable.getPageNumber())
                .size(commentDetailDtoList.size())
                .votingState(postVotingStateRepository.findByUserAndPost(user, post))
                .commentList(commentDetailDtoList)
                .build();
    }

    @Override
    public WebPostDetailResponse toResponse(WebPostDetailDto dto) {
        if(dto.getVotingState().isPresent()){
            return WebPostDetailResponse.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .firstImageUrl(dto.getFirstImageUrl())
                    .secondImageUrl(dto.getSecondImageUrl())
                    .profileImageUrl(dto.getProfileImageUrl())
                    .firstVotingOption(dto.getFirstVotingOption())
                    .secondVotingOption(dto.getSecondVotingOption())
                    .firstVotingCount(dto.getFirstVotingCount())
                    .secondVotingCount(dto.getSecondVotingCount())
                    .writer(dto.getWriter())
                    .page(dto.getPage())
                    .size(dto.getSize())
                    .votingState(dto.getVotingState().get().getVote())
                    .commentList(dto.getCommentList())
                    .build();
        } else {
            return WebPostDetailResponse.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .firstImageUrl(dto.getFirstImageUrl())
                    .secondImageUrl(dto.getSecondImageUrl())
                    .profileImageUrl(dto.getProfileImageUrl())
                    .firstVotingOption(dto.getFirstVotingOption())
                    .secondVotingOption(dto.getSecondVotingOption())
                    .firstVotingCount(dto.getFirstVotingCount())
                    .secondVotingCount(dto.getSecondVotingCount())
                    .writer(dto.getWriter())
                    .page(dto.getPage())
                    .size(dto.getSize())
                    .votingState(0)
                    .commentList(dto.getCommentList())
                    .build();
        }
    }

    @Override
    public PostListDto toDto(List<Post> list, Pageable pageable) {
        return PostListDto.builder()
                .posts(list)
                .pageable(pageable)
                .build();
    }

    @Override
    public PostListResponse toResponse(List<PostResponse> postResponses, int pageNumber) {
        return PostListResponse.builder()
                .page(pageNumber)
                .size(postResponses.size())
                .postList(postResponses)
                .build();
    }

    @Override
    public WebPostListResponse toWebResponse(List<WebPostResponse> webVerPostResponseList, int pageNumber) {
        return WebPostListResponse.builder()
                .page(pageNumber)
                .size(webVerPostResponseList.size())
                .postList(webVerPostResponseList)
                .build();
    }

    @Override
    public List<WebMyPagePostDto> toWebMyPagePostDto(List<Post> postList, User user) {
        return postList.stream().map( entity ->
                new WebMyPagePostDto(
                        entity.getIdx(),
                        entity.getFirstImageUrl(),
                        entity.getTitle(),
                        entity.getFirstVotingOption(),
                        entity.getSecondVotingOption(),
                        entity.getFirstVotingCount() + entity.getSecondVotingCount(),
                        commentRepository.countByPost(entity)
                )
        ).collect(Collectors.toList());
    }

    @Override
    public Post toEntity(CreatePostDto dto, User user) {
        return new Post(
                dto.getTitle(),
                dto.getContent(),
                dto.getFirstVotingOption(),
                dto.getSecondVotingOption(),
                dto.getFirstImageUrl(),
                dto.getSecondImageUrl(),
                0,
                0,
                user,
                LocalDate.now().toString()     
                );
    }

    @Override
    public CreatePostDto toDto(CreatePostRequest requestDto){
        return CreatePostDto.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .firstVotingOption(requestDto.getFirstVotingOption())
                .secondVotingOption(requestDto.getSecondVotingOption())
                .firstImageUrl(requestDto.getFirstImageUrl())
                .secondImageUrl(requestDto.getSecondImageUrl())
                .build();
    }
    @Override
    public PostDetailResponse toResponse(PostDetailDto postDetailDto) {
        return PostDetailResponse.builder()
                .writer(postDetailDto.getWriter())
                .image(postDetailDto.getImage())
                .page(postDetailDto.getPageable().getPageNumber())
                .size(postDetailDto.getCommentList().size())
                .commentList(postDetailDto.getCommentList())
                .build();
    }
    @Override
    public PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList, Post post, Pageable pageable) {
        return PostDetailDto.builder()
                .writer(post.getUser().getNickname())
                .image(post.getUser().getProfileImageUrl())
                .commentList(commentDetailDtoList)
                .pageable(pageable)
                .build();
    }

    @Override
    public VoteForPostResponse toResponse(VoteForPostDto voteCountDto) {
        return VoteForPostResponse.builder()
                .firstVotingCount(voteCountDto.getFirstVotingCount())
                .secondVotingCount(voteCountDto.getSecondVotingCount())
                .build();
    }

    @Override
    public VoteForPostDto toDto(Integer firstVotingCount, Integer secondVotingCount) {
        return VoteForPostDto.builder()
                .firstVotingCount(firstVotingCount)
                .secondVotingCount(secondVotingCount)
                .build();
    }

    @Override
    public PostVotingState toEntity(User user, Post post) {
        return PostVotingState.builder()
                .vote(0)
                .user(user)
                .post(post)
                .build();
    }

    @Override
    public VoteOptionDto toDto(VoteOptionRequest voteOptionRequest) {
        Integer choice = voteOptionRequest.getChoice();
        return VoteOptionDto.builder()
                .choice(choice)
                .build();
    }

    @Override
    public List<WebMyPagePostResponse> toWebMyPagePostResponse(List<WebMyPagePostDto> postList) {
        return postList.stream().map(dto ->
                new WebMyPagePostResponse(
                        dto.getIdx(),
                        dto.getTitle(),
                        dto.getImageUrl(),
                        dto.getFirstVotingOption(),
                        dto.getSecondVotingOption(),
                        dto.getParticipants(),
                        dto.getCommentCount()
                )).collect(Collectors.toList());
    }
}


