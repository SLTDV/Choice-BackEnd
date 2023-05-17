package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.TotalPageAndWebPostDtoList;
import com.select.choice.domain.post.presentation.data.dto.WebPostDto;
import com.select.choice.domain.post.service.GetLatestPostsService;
import com.select.choice.domain.post.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetLatestPostServiceImpl implements GetLatestPostsService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Override
    public List<PostDto> getLatestPost(Pageable pageable) {
        List<Post> list = postRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idx").descending()))
                .toList();
        return postConverter.toDto(list);
    }

    @Override
    public TotalPageAndWebPostDtoList getLatestPostList(Pageable pageable) {
        List<Post> list = postRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idx").descending()))
                .toList();
        Integer totalPage = postRepository.findAll().size() / pageable.getPageSize();
        List<WebPostDto> webPostDtoList = postConverter.toPostDto(list);

        return postConverter.toDto(totalPage, webPostDtoList);
    }
}
