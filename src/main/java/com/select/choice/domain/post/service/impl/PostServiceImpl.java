package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.comment.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.presentation.data.response.AddCountResponse;
import com.select.choice.domain.post.exception.IsNotMyPostException;
import com.select.choice.domain.post.presentation.data.response.PostDetailResponse;
import com.select.choice.domain.post.exception.PostNotFoundException;
import com.select.choice.domain.post.service.PostService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserFacade userFacade;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Override
    public List<PostDto> getAllPostList() {
        List<Post> list = postRepository.findAll();
        return postConverter.toDto(list);
    }

    @Override
    public List<PostDto> getBestPostList() {
        List<Post> list = postRepository.getBestPostList();
        return postConverter.toBestPostDto(list);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void createPost(CreatePostDto postDto) {
        User user = userFacade.currentUser();
        Post post = postConverter.toEntity(postDto, user);
        postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PostDetailDto aggregateDetail(Long postIdx) {
        User user = userFacade.currentUser();

        List<Comment> comment = commentRepository.findAllByPostIdx(postIdx);

        List<CommentDetailDto> commentDetailDtoList = commentConverter.toDto(comment);

        return postConverter.toDto(commentDetailDtoList,user);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void deletePost(Long postIdx) {
        User user = userFacade.currentUser();
        Post post = postRepository.findById(postIdx)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND));

        if(!Objects.equals(post.getUser(), user))
            throw new IsNotMyPostException(ErrorCode.IS_NOT_MY_POST);

        postRepository.delete(post);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public VoteCountDto addCount(AddCountDto addCountDto, Long postIdx) {
        Post post = postRepository.findById(postIdx).orElseThrow(()->new PostNotFoundException(ErrorCode.POST_NOT_FOUND));

        Integer choice = addCountDto.getChoice();
        if(choice == 0){
            post.updateFirstVotingCount();
        } else
            post.updateSecondVotingCount();
        post.updateIsVoting();

        return postConverter.toDto(post.getFirstVotingCount(), post.getSecondVotingCount());
    }
}
