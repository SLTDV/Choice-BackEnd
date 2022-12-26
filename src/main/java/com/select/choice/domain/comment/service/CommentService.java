package com.select.choice.domain.comment.service;

import com.select.choice.domain.comment.data.dto.CommentDto;

public interface CommentService {
    void write(Long postIdx,CommentDto commentDto);

    void edit(Long commentIdx, CommentDto commentDto);

    void delete(Long commentIdx);
}
