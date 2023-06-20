package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.TotalPageAndWebPostDtoList;
import com.select.choice.domain.post.presentation.data.dto.WebPostDto;
import com.select.choice.domain.post.service.GetPopularPostsService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPopularPostServiceImpl implements GetPopularPostsService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserUtil userUtil;

    @Override
    public List<PostDto> getPopularPosts(Pageable pageable) {
        return postConverter.toDto(getSortPost(pageable));
    }

    @Override
    public TotalPageAndWebPostDtoList getPopularPostList(Pageable pageable) {
        Integer totalPage = postRepository.findAll().size() / pageable.getPageSize();
        List<WebPostDto> webPostDtoList = postConverter.toPostDto(getSortPost(pageable));

        return postConverter.toDto(totalPage, webPostDtoList);
    }

    private List<Post> getSortPost(Pageable pageable) {
        User currentUser = userUtil.currentUser();
        List<Post> list = postRepository.getPopularPosts();

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
}
