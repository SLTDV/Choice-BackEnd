package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.post.presentation.data.dto.PostDetailDto;
import com.select.choice.domain.post.presentation.data.dto.WebPostDetailDto;
import com.select.choice.domain.post.service.GetPostDetailService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.post.util.PostUtil;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostDetailServiceImpl implements GetPostDetailService {
    private final UserUtil userUtil;
    private final PostUtil postUtil;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;
    private final PostConverter postConverter;

    @Override
    public PostDetailDto getPostDetail(Long postIdx, Pageable pageable) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);
        List<Comment> comment = commentRepository.findAllByPostIdx(
                postIdx,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idx").descending()));
        List<CommentDetailDto> commentDetailDtoList = commentConverter.toDto(comment, user);

        return postConverter.toDto(commentDetailDtoList, post, pageable, user);
    }

    @Override
    public WebPostDetailDto getWebPostDetail(Long postIdx, Pageable pageable) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);
        List<Comment> comment = commentRepository.findAllByPostIdx(
                postIdx,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idx").descending()));
        List<CommentDetailDto> commentDetailDtoList = commentConverter.toDto(comment, user);

        return postConverter.toPostDetailDto(commentDetailDtoList, post, pageable, user);
    }
}
