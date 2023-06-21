package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.PostDto;
import com.select.choice.domain.post.presentation.data.dto.TotalPageAndWebPostDtoList;
import com.select.choice.domain.post.service.GetPopularPostsService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPopularPostServiceImpl implements GetPopularPostsService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserUtil userUtil;

    @Override
    public List<PostDto> getPopularPosts(Pageable pageable) {
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
        List<Post> filteredAndPaginationList = new PageImpl<>(pageContent, pageable, filteredList.size()).toList();

        return postConverter.toDto(filteredAndPaginationList);
    }

    @Override
    public TotalPageAndWebPostDtoList getPopularPostList(Optional<String> token, Pageable pageable) {
            if(token.isPresent()) {
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
                List<Post> filteredAndPaginationList = new PageImpl<>(pageContent, pageable, filteredList.size()).toList();

                Integer totalPage = filteredList.size() / pageable.getPageSize();
                return postConverter.toDto(totalPage, postConverter.toPostDto(filteredAndPaginationList));
            } else {
                List<Post> postList = postRepository.getPopularPosts(pageable);
                Integer totalPage = postRepository.findAll().size() / pageable.getPageSize();
                return postConverter.toDto(totalPage, postConverter.toPostDto(postList));
            }
    }
}
