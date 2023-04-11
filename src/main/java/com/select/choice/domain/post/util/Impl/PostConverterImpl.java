package com.select.choice.domain.post.util.Impl;

import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.entity.PostVotingStatus;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.presentation.data.response.*;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverterImpl implements PostConverter {
    private final UserUtil userUtil;
    private final CommentRepository commentRepository;
    @Override
    public List<PostResponse> toBesetPostResponse(List<PostDto> dto){
        return dto.stream().map(post ->
                    new PostResponse(
                            post.getIdx(),
                            post.getFirstImageUrl(),
                            post.getSecondImageUrl(),
                            post.getTitle(),
                            post.getContent(),
                            post.getFirstVotingOption(),
                            post.getSecondVotingOption(),
                            post.getFirstVotingCount(),
                            post.getSecondVotingCount(),
                            post.getVotingPost().get(0).getVote(),
                            post.getParticipants(),
                            post.getCommentCount()
                    )
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> toBestPostDto(List<Post> entity) {
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
                        post.getPostVotingStatuses().stream().filter(
                                votingPost -> votingPost.getUser().getEmail().equals(user.getEmail())
                                        & votingPost.getPost().getIdx().equals(post.getIdx())
                        ).collect(Collectors.toList()),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        commentRepository.countByPost(post)
                )
        ).collect(Collectors.toList());
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
                        post.getPostVotingStatuses().stream().filter(
                                votingPost -> votingPost.getUser().getEmail().equals(user.getEmail())
                                        & votingPost.getPost().getIdx().equals(post.getIdx())
                        ).collect(Collectors.toList()),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        commentRepository.countByPost(post)
                )
        ).sorted(Comparator.comparing(PostDto::getIdx).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<WebVerPostDto> toPostDto(List<Post> list) {
        return list.stream().map(post ->
                new WebVerPostDto(
                        post.getIdx(),
                        post.getFirstImageUrl(),
                        post.getTitle(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        commentRepository.countByPost(post)
                )
        ).sorted(Comparator.comparing(WebVerPostDto::getIdx).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<WebVerPostResponse> toPostResponse(List<WebVerPostDto> webVerPostDtoList) {
        return webVerPostDtoList.stream().map(dto ->
                new WebVerPostResponse(
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
    public List<WebVerPostDto> toBestPostDtoList(List<Post> list) {
        return list.stream().map(entity ->
                new WebVerPostDto(
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
    public List<WebVerPostResponse> toBesetPostDtoResponse(List<WebVerPostDto> bestPostList) {
        return bestPostList.stream().map(dto ->
                new WebVerPostResponse(
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
    public TodayPostListDto toTodayPostListDto(List<TodayPostDto> todayPosts) {
        return TodayPostListDto.builder()
                .todayPosts(todayPosts)
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
                .todayPosts(todayPostListResponses)
                .build();
    }

    @Override
    public WebVerPostDetailDto toPostDetailDto(List<CommentDetailDto> commentDetailDtoList, Post post, User user) {
        return WebVerPostDetailDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .firstImageUrl(post.getFirstImageUrl())
                .secondImageUrl(post.getSecondImageUrl())
                .profileImageUrl(user.getProfileImageUrl())
                .writer(post.getUser().getNickname())
                .comment(commentDetailDtoList)
                .build();

    }

    @Override
    public WebVerPostDetailResponse toResponse(WebVerPostDetailDto dto) {
        return WebVerPostDetailResponse.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .firstImageUrl(dto.getFirstImageUrl())
                .secondImageUrl(dto.getSecondImageUrl())
                .profileImageUrl(dto.getProfileImageUrl())
                .writer(dto.getWriter())
                .comment(dto.getComment())
                .build();
    }


    @Override
    public List<PostResponse> toResponse(List<PostDto> dto) {
        return dto.stream().map(post ->
                new PostResponse(
                        post.getIdx(),
                        post.getFirstImageUrl(),
                        post.getSecondImageUrl(),
                        post.getTitle(),
                        post.getContent(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount(),
                        post.getVotingPost().get(0).getVote(),
                        post.getParticipants(),
                        post.getCommentCount()
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
                .comment(postDetailDto.getComment())
                .build();
    }
    @Override
    public PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList, Post post) {
        return PostDetailDto.builder()
                .writer(post.getUser().getNickname())
                .image(post.getUser().getProfileImageUrl())
                .comment(commentDetailDtoList)
                .build();
    }

    @Override
    public VoteCountResponse toResponse(VoteCountDto voteCountDto) {
        return VoteCountResponse.builder()
                .firstVotingCount(voteCountDto.getFirstVotingCount())
                .secondVotingCount(voteCountDto.getSecondVotingCount())
                .build();
    }

    @Override
    public VoteCountDto toDto(Integer firstVotingCount, Integer secondVotingCount) {
        return VoteCountDto.builder()
                .firstVotingCount(firstVotingCount)
                .secondVotingCount(secondVotingCount)
                .build();
    }

    @Override
    public PostVotingStatus toEntity(User user, Post post) {
        return PostVotingStatus.builder()
                .vote(0)
                .user(user)
                .post(post)
                .build();
    }

    @Override
    public AddCountDto toDto(AddCountRequest addCountRequest) {
        int choice = addCountRequest.getChoice();
        return AddCountDto.builder()
                .choice(choice)
                .build();
    }
}


