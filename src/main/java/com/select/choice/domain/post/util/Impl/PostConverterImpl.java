package com.select.choice.domain.post.util.Impl;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.entity.VotingPost;
import com.select.choice.domain.post.domain.repository.VotingPostRepository;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.presentation.data.request.CreatePostRequest;
import com.select.choice.domain.post.presentation.data.response.VoteCountResponse;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.request.AddCountRequest;
import com.select.choice.domain.post.presentation.data.response.PostResponse;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverterImpl implements PostConverter {
    private final UserUtil userUtil;
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
                        post.getVotingPost().stream().filter(
                                votingPost -> votingPost.getUser().getEmail() == user.getEmail()
                                        & votingPost.getPost().getIdx() == post.getIdx()
                        ).collect(Collectors.toList()),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        post.getCommentCount()
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
                        post.getVotingPost().stream().filter(
                                votingPost -> votingPost.getUser().getEmail() == user.getEmail()
                                        & votingPost.getPost().getIdx() == post.getIdx()
                        ).collect(Collectors.toList()),
                        post.getFirstVotingCount() + post.getSecondVotingCount(),
                        post.getCommentCount()

                )
        ).sorted(Comparator.comparing(PostDto::getIdx).reversed()).collect(Collectors.toList());
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
                0
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
                .comment(postDetailDto.getComment())
                .build();
    }
    @Override
    public PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList, User user) {
            return PostDetailDto.builder()
                .writer(user.getNickname())
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
    public VotingPost toEntity(int choiceOption, User user, Post post) {
        return VotingPost.builder()
                .vote(choiceOption)
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


