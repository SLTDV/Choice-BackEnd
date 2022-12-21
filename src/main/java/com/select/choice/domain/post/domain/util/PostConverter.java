package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostConverter {
    ResponseEntity<List<PostResponse>> toResponse(List<PostDto> dto);
}
