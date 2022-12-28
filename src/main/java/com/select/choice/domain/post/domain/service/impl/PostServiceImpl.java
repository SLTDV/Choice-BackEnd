package com.select.choice.domain.post.domain.service.impl;

import com.select.choice.domain.comment.domain.data.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.post.domain.data.dto.AddCountDto;
import com.select.choice.domain.post.domain.data.dto.CreatePostDto;
import com.select.choice.domain.post.domain.data.dto.PostDetailDto;
import com.select.choice.domain.post.domain.data.dto.PostDto;
import com.select.choice.domain.post.domain.data.entity.Post;
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

    @Override
    public List<PostDto> getAllPostList() {
        List<Post> list = postRepository.findAll();
        return postConverter.toPostDto(list);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getBestPostList() {
        List<Post> list = postRepository.getBestPostList();
        return postConverter.toPostDto(list);
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
        Post post = postRepository.findByIdx(postIdx);
        List<Comment> comment = commentRepository.findAllByPostIdx(postIdx);
        PostDetailDto postDetailDto = postConverter.postDetailDto(post,comment,user);
        return postConverter.toDetailResponse(postDetailDto);
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
    public void addCount(AddCountDto addCountDto, Long postIdx) {
        Post post = postRepository.findById(postIdx).orElseThrow(()->new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        Integer choice = addCountDto.getChoice();
        if(choice == 0){
            post.updateFirstVotingCount();
        } else
            post.updateSecondVotingCount();
    }

}
