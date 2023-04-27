package com.select.choice.domain.post.service;

import com.select.choice.domain.post.presentation.data.dto.PostDetailDto;
import com.select.choice.domain.post.presentation.data.dto.WebPostDetailDto;
import org.springframework.data.domain.Pageable;

public interface GetPostDetailService {
    PostDetailDto getPostDetail(Long postIdx, Pageable pageable);
    WebPostDetailDto getWebPostDetail(Long postIdx, Pageable pageable);
}
