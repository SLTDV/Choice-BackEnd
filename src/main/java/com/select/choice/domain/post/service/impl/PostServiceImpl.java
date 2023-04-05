package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.presentation.data.dto.CommentDetailDto;
import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.comment.domain.repository.CommentRepository;
import com.select.choice.domain.comment.util.CommentConverter;
import com.select.choice.domain.post.domain.entity.PostVotingStatus;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.repository.PostVotingStatusRepository;
import com.select.choice.domain.post.exception.InvalidChoiceException;
import com.select.choice.domain.post.exception.InvalidVoteCount;
import com.select.choice.domain.post.presentation.data.dto.*;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.exception.IsNotMyPostException;
import com.select.choice.domain.post.presentation.data.dto.TodayPostListDto;
import com.select.choice.domain.post.service.PostService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.post.util.PostUtil;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.domain.repository.UserRepository;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserUtil userUtil;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;
    private final PostUtil postUtil;
    private final PostVotingStatusRepository postVotingStatusRepository;
    private final UserRepository userRepository;

    @Override
    public List<PostDto> getAllPostList() {
        List<Post> list = postRepository.findAll();
        return postConverter.toDto(list);
    }

    @Override
    public List<WebVerPostDto> getPost() {
        List<Post> list = postRepository.findAll();
        return postConverter.toPostDto(list);
    }

    @Override
    public List<WebVerPostDto> getBestPost() {
        List<Post> list = postRepository.getBestPostList();
        return postConverter.toBestPostDtoList(list);
    }

    @Override
    public TodayPostListDto getTodayPostList() {
        String today = LocalDate.now().toString();
        List<TodayPostDto> todayPosts = postRepository.findAllByCreatedAtContaining(today).stream()
                .map(postConverter::toTodayPostDto)
                .sorted(Comparator.comparing(TodayPostDto::getParticipants).reversed()).collect(Collectors.toList());

        return postConverter.toTodayPostListDto(todayPosts);
    }

    @Override
    public List<PostDto> getBestPostList() {
        List<Post> list = postRepository.getBestPostList();
        return postConverter.toBestPostDto(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPost(CreatePostDto postDto) {
        User currentUser = userUtil.currentUser();
        Post post = postConverter.toEntity(postDto, currentUser);
        postRepository.save(post);

        List<User> userList = userRepository.findAll();
        for(User user: userList) {
            PostVotingStatus postVotingStatus = postConverter.toEntity(user, post);
            postVotingStatusRepository.save(postVotingStatus);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PostDetailDto aggregateDetail(Long postIdx) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);
        List<Comment> comment = commentRepository.findAllByPostIdx(postIdx);
        List<CommentDetailDto> commentDetailDtoList = commentConverter.toDto(comment, user);

        return postConverter.toDto(commentDetailDtoList, post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePost(Long postIdx) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);

        if(!Objects.equals(post.getUser(), user))
            throw new IsNotMyPostException(ErrorCode.IS_NOT_MY_POST);

        postRepository.delete(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VoteCountDto voteCount(AddCountDto addCountDto, Long postIdx) {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);
        PostVotingStatus voting = postVotingStatusRepository.findByUserAndPost(user, post);
        int choiceOption = addCountDto.getChoice();

        if(!(choiceOption == 1 | choiceOption == 2)) {
            throw new InvalidChoiceException(ErrorCode.INVALID_CHOICE);
        }
        post.updateVotingCount(voting.getVote(), choiceOption);


        if (post.getFirstVotingCount() < 0 | post.getSecondVotingCount() < 0) {
            throw new InvalidVoteCount(ErrorCode.INVALID_VOTE_COUNT);
        }
        voting.updateVote(choiceOption);

        return postConverter.toDto(post.getFirstVotingCount(), post.getSecondVotingCount());
    }

}
