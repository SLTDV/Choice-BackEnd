package com.select.choice.domain.post.domain.service.impl;

import com.select.choice.domain.comment.domain.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.domain.data.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.domain.util.CommentConverter;
import com.select.choice.domain.post.domain.data.dto.*;
import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.post.domain.data.response.AddCountResponse;
import com.select.choice.domain.post.domain.data.response.PostListResponse;
import com.select.choice.domain.post.domain.exception.IsNotMyPostException;
import com.select.choice.domain.post.domain.data.response.PostDetailResponse;
import com.select.choice.domain.post.domain.exception.PostNotFoundException;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.service.PostService;
import com.select.choice.domain.post.domain.util.PostConverter;
import com.select.choice.domain.user.domain.data.entity.User;
import com.select.choice.domain.user.domain.facade.UserFacade;
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
    public List<PostListResponse> getAllPostList() {
        List<Post> list = postRepository.findAll();
        List<PostListDto> postListDtoList = postConverter.toPostListDto(list);
        return postConverter.toResponse(postListDtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostListResponse> getBestPostList() {
        List<Post> list = postRepository.getBestPostList();
        List<PostListDto> postListDtoList = postConverter.toBestPostDto(list);
        return postConverter.toResponse(postListDtoList);
    }
    @Transactional
    @Override
    public void createPost(CreatePostDto postDto) {
        User user = userFacade.currentUser();
        Post post = postConverter.toEntity(postDto, user);
        postRepository.save(post);
    }

    @Override
    public PostDetailResponse aggregateDetail(Long postIdx) {
        User user = userFacade.currentUser();
        List<Comment> comment = commentRepository.findAllByPostIdx(postIdx);
        List<CommentDetailDto> commentDetailDtoList = commentConverter.toDto(comment);
        PostDetailDto postDetailDto = postConverter.toDto(commentDetailDtoList,user);
        return postConverter.toResponse(postDetailDto);
    }

    @Override
    public void deletePost(Long postIdx) {
        User user = userFacade.currentUser();
        Post post = postRepository.findById(postIdx).orElseThrow(()->new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        if(!Objects.equals(post.getUser().getIdx(), user.getIdx()))
            throw new IsNotMyPostException(ErrorCode.IS_NOT_MY_POST);
        postRepository.delete(post);
    }

    @Transactional
    @Override
    public AddCountResponse addCount(AddCountDto addCountDto, Long postIdx) {
        Post post = postRepository.findById(postIdx).orElseThrow(()->new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        Integer choice = addCountDto.getChoice();
        if(choice == 0){
            post.updateFirstVotingCount();
        } else
            post.updateSecondVotingCount();

        post.updateIsVoting();
        return postConverter.toResponse(post.getFirstVotingCount(), post.getSecondVotingCount());
    }
}
