package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.comment.data.entity.Comment;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDetailDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.data.request.CreatePostRequestDto;
import com.select.choice.domain.user.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverterImpl implements PostConverter{
    @Override
    public List<PostResponse> toResponse(List<PostDto> dto){

        return dto.stream().map(post ->
                    new PostResponse(
                            post.getIdx(),
                            post.getContent(),
                            post.getTitle(),
                            post.getThumbnail(),
                            post.getFirstVotingOption(),
                            post.getSecondVotingOption(),
                            post.getFirstVotingCount(),
                            post.getSecondVotingCount()
                    )
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> toPostDto(List<Post> entity) {
        return entity.stream().map(post ->
                new PostDto(
                        post.getIdx(),
                        post.getContent(),
                        post.getTitle(),
                        post.getThumbnail(),
                        post.getFirstVotingOption(),
                        post.getSecondVotingOption(),
                        post.getFirstVotingCount(),
                        post.getSecondVotingCount()
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
                user
        );
    }

    @Override
    public CreatePostDto toCreatePost(CreatePostRequestDto requestDto){
        return CreatePostDto.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .firstVotingOption(requestDto.getFirstVotingOption())
                .secondVotingOption(requestDto.getSecondVotingOption())
                .thumbnail(requestDto.getThumbnail())
                .build();
    }
    @Override
    public PostDetailResponse toDetailResponse(PostDetailDto postDetailDto) {
        return PostDetailResponse.builder()
                .authorname(postDetailDto.getAuthorname())
                .title(postDetailDto.getTitle())
                .content(postDetailDto.getContent())
                .firstVotingOption(postDetailDto.getFirstVotingOption())
                .secondVotingOption(postDetailDto.getSecondVotingOption())
                .firstVotingCount(postDetailDto.getFirstVotingCount())
                .secondVotingCount(postDetailDto.getSecondVotingCount())
                .build();

    }
}

