package com.select.choice.domain.post.domain.util.Impl;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import com.select.choice.domain.post.domain.data.dto.*;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.request.AddCountRequest;
import com.select.choice.domain.post.domain.data.response.AddCountResponse;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.response.PostListResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.post.domain.util.PostConverter;
import com.select.choice.domain.user.domain.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverterImpl implements PostConverter {
    @Override
    public List<PostListResponse> toResponse(List<PostListDto> dto){
        return dto.stream().map(post ->
                    new PostListResponse(
                            post.getIdx(),
                            post.getThumbnail(),
                            post.getTitle(),
                            post.getContent(),
                            post.getFirstVotingOption(),
                            post.getSecondVotingOption(),
                            post.getFirstVotingCount(),
                            post.getSecondVotingCount(),
                            post.isIsVoting()
                    )
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostListDto> toPostListDto(List<Post> entity) {
        return entity.stream().map(post ->
                new PostListDto(
                        post.getIdx(),
                        post.getThumbnail(),
                        post.getTitle(),
                        post.getContent(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount(),
                        post.isIsVoting()
                )
        ).sorted(Comparator.comparing(PostListDto::getIdx).reversed()).collect(Collectors.toList());
    }


    @Override
    public List<PostListDto> toBestPostDto(List<Post> entity) {
        return entity.stream().map(post ->
                new PostListDto(
                        post.getIdx(),
                        post.getThumbnail(),
                        post.getTitle(),
                        post.getContent(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount(),
                        post.isIsVoting()
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
                dto.getThumbnail(),
                0,
                0,
                user
        );
    }

    @Override
    public CreatePostDto toDto(CreatePostRequestDto requestDto){
        return CreatePostDto.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .firstVotingOption(requestDto.getFirstVotingOption())
                .secondVotingOption(requestDto.getSecondVotingOption())
                .thumbnail(requestDto.getThumbnail())
                .build();
    }
    @Override
    public PostDetailResponse toResponse(PostDetailDto postDetailDto) {
        return PostDetailResponse.builder()
                .authorname(postDetailDto.getAuthorname())
                .comment(postDetailDto.getComment())
                .build();
    }
    @Override
    public PostDetailDto toDto(List<CommentDetailDto> commentDetailDtoList, User user) {
            return PostDetailDto.builder()
                .authorname(user.getNickname())
                .comment(commentDetailDtoList)
                .build();
    }

    @Override
    public AddCountResponse toResponse(Integer firstVotingCount, Integer secondVotingCount) {
        return AddCountResponse.builder()
                .firstVotingCount(firstVotingCount)
                .secondVotingCount(secondVotingCount)
                .build();
    }

    @Override
    public AddCountDto toDto(AddCountRequest addCountRequest) {
        Integer choice = addCountRequest.getChoice();
        return AddCountDto.builder()
                .choice(choice)
                .build();
    }
}


