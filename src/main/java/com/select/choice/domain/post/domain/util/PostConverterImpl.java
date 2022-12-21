package com.select.choice.domain.post.domain.util;

import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.reponse.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PostConverterImpl implements PostConverter{

    @Override
    public ResponseEntity<List<PostResponse>> toResponse(List<PostDto> dto){

        dto.stream()
                .map(el -> el = ge)
    }
}
