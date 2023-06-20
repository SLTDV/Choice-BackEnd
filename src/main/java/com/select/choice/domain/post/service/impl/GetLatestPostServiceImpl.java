package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.TotalPageAndWebPostDtoList;
import com.select.choice.domain.post.presentation.data.dto.WebPostDto;
import com.select.choice.domain.post.service.GetLatestPostsService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLatestPostServiceImpl implements GetLatestPostsService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserUtil userUtil;

    @Override
    public List<PostDto> getLatestPost(Pageable pageable) {
        return postConverter.toDto(getSortPost(pageable));
    }

    @Override
    public TotalPageAndWebPostDtoList getLatestPostList(Pageable pageable) {
        Integer totalPage = postRepository.findAll().size() / pageable.getPageSize();
        if(!SecurityContextHolder.getContext().getAuthentication().getName().isEmpty()) {
            List<WebPostDto> webPostDtoList = postConverter.toPostDto(noAuth(pageable));
            return postConverter.toDto(totalPage, webPostDtoList);
        } else {
            List<WebPostDto> webPostDtoList = postConverter.toPostDto(getSortPost(pageable));
            return postConverter.toDto(totalPage, webPostDtoList);
        }
    }

    private List<Post> getSortPost(Pageable pageable) {
        User currentUser = userUtil.currentUser();
        List<Post> list = postRepository.findAll(Sort.by("idx").descending());

        List<Post> filteredList = list.stream()
                .filter(post -> {
                    boolean isBlockedByCurrentUser = currentUser.getBlockedUsers().stream()
                            .anyMatch(blockedUser -> blockedUser.getBlockedUser().equals(post.getUser()));
                    boolean isBlockedByOtherUser = currentUser.getBlockingUsers().stream()
                            .anyMatch(blockedUser -> blockedUser.getBlockingUser().equals(post.getUser()));
                    return !isBlockedByCurrentUser && !isBlockedByOtherUser;
                }).collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filteredList.size());
        List<Post> pageContent = filteredList.subList(start, end);

        return new PageImpl<>(pageContent, pageable, filteredList.size()).toList();
    }

    private List<Post> noAuth(Pageable pageable) {
        return postRepository.findAll(
                        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idx").descending()))
                .toList();
    }
}
