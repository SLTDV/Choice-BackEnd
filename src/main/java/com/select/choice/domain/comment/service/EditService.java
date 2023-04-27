package com.select.choice.domain.comment.service;

import com.select.choice.domain.comment.presentation.data.dto.CommentDto;

public interface EditService {
    void edit(Long commentIdx, CommentDto commentDto);
}
