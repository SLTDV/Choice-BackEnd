package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.TotalPageAndWebPostDtoList;
import com.select.choice.domain.post.presentation.data.dto.WebPostDto;
import com.select.choice.domain.post.service.GetLatestPostsService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.domain.repository.BlockedUserRepository;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLatestPostServiceImpl implements GetLatestPostsService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserUtil userUtil;
    private final BlockedUserRepository blockedUserRepository;

    @Override
    public List<PostDto> getLatestPost(Pageable pageable) {
        User currentUser = userUtil.currentUser();
        List<Post> list = postRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idx").descending()))
                .toList();

        List<Post> filteredList = list.stream()
                .filter(post -> {
                    boolean isBlockedByCurrentUser = currentUser.getBlockedUsers().stream()
                            .anyMatch(blockedUser -> blockedUser.getBlockedUser().equals(post.getUser()));
                    boolean isBlockedByOtherUser = currentUser.getBlockingUsers().stream()
                            .anyMatch(blockedUser -> blockedUser.getBlockingUser().equals(post.getUser()));
                    return !isBlockedByCurrentUser && !isBlockedByOtherUser;
                })
                .collect(Collectors.toList());

        return postConverter.toDto(filteredList);
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
