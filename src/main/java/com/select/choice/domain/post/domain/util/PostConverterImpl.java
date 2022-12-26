package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.PostResponse;
import com.select.choice.domain.post.domain.request.CreatePostRequestDto;
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
    public Post toEntity(CreatePostDto dto) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .thumbnail(dto.getThumbnail())
                .firstVotingOption(dto.getFirstVotingOption())
                .secondVotingOption(dto.getSecondVotingOtion())
                .build();
    }

    @Override
    public List<CreatePostDto> toCreatePost(List<CreatePostRequestDto> requestDtos){
        return requestDtos.stream().map(dto ->
                new CreatePostDto(
                        dto.getTitle(),
                        dto.getContent(),
                        dto.getThumbnail(),
                        dto.getFirstVotingOption(),
                        dto.getSecondVotingOtion()
                )
        ).collect(Collectors.toList());
    }
}


