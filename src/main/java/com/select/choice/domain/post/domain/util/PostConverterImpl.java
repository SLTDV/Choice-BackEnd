package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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
}

