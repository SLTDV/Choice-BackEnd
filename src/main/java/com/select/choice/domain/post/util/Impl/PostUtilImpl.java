package com.select.choice.domain.post.util.Impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.exception.PostNotFoundException;
import com.select.choice.domain.post.util.PostUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PostUtilImpl implements PostUtil {
    private final PostRepository postRepository;

    public Post findById(Long idx){
        return postRepository.findById(idx)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
    }
}
