package com.select.choice.domain.comment.service;

import com.select.choice.domain.comment.presentation.data.dto.CommentDto;

public interface WriteService {
    void write(Long postIdx, CommentDto commentDto);
}
