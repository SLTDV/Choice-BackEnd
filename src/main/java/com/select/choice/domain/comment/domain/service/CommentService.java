package com.select.choice.domain.comment.domain.service;

import com.select.choice.domain.comment.domain.data.dto.CommentDto;

public interface CommentService {
    void write(Long postIdx,CommentDto commentDto);

    void edit(Long commentIdx, CommentDto commentDto);

    void delete(Long commentIdx);
}
