package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import com.select.choice.domain.post.domain.exception.PostNotFoundException;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.request.CreatePostRequestDto;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverterImpl implements PostConverter{
    private final PostRepository postRepository;
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
    public List<CreatePostRequestDto> toCreatePost(List<CreatePostRequestDto> createPostRequestDto){
        return createPostRequestDto.stream().map(dto ->
                new CreatePostRequestDto(
                        dto.getTitle(),
                        dto.getContent(),
                        dto.getThumbnail(),
                        dto.getFirstVotingOption(),
                        dto.getSecondVotingOtion()
                )
        ).collect(Collectors.toList());
               /* .title(createPostRequestDto.getTitle())
                .content(createPostRequestDto.getContent())
                .thumbnail(createPostRequestDto.getThumbnail())
                .firstVotingOption(createPostRequestDto.getFirstVotingOption())
                .secondVotingOtion(createPostRequestDto.getSecondVotingOtion());*/
    }
}


