package com.select.choice.domain.post.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.PostVotingState;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.repository.PostVotingStateRepository;
import com.select.choice.domain.post.exception.InvalidChoiceException;
import com.select.choice.domain.post.exception.InvalidVoteCount;
import com.select.choice.domain.post.presentation.data.dto.VoteOptionDto;
import com.select.choice.domain.post.presentation.data.dto.VoteForPostDto;
import com.select.choice.domain.post.service.VoteForPostService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.post.util.PostUtil;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class VoteForPostServiceImpl implements VoteForPostService {
    private final UserUtil userUtil;
    private final PostUtil postUtil;
    private final PostVotingStateRepository postVotingStateRepository;
    private final PostConverter postConverter;
    private final PostRepository postRepository;
    private final FirebaseMessaging firebaseMessaging;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VoteForPostDto voteForPost(VoteOptionDto addCountDto, Long postIdx) throws FirebaseMessagingException {
        User user = userUtil.currentUser();
        Post post = postUtil.findById(postIdx);
        int choiceOption = addCountDto.getChoice();

        PostVotingState voting;
        if(postVotingStateRepository.existsByUserAndPost(user, post)) {
            voting = postVotingStateRepository.findPostVotingStatusByUserAndPost(user, post);
        } else {
            voting = postConverter.toEntity(user, post);
        }
        post.updateVotingCount(voting.getVote(), choiceOption);
        postRepository.save(post);

        if(!(choiceOption == 1 | choiceOption == 2)) {
            throw new InvalidChoiceException(ErrorCode.INVALID_CHOICE);
        } else if (post.getFirstVotingCount() < 0 | post.getSecondVotingCount() < 0) {
            throw new InvalidVoteCount(ErrorCode.INVALID_VOTE_COUNT);
        }

        voting.updateVote(choiceOption);
        postVotingStateRepository.save(voting);

        sendNotification(voting.getVote(), voting.getUser());

        return postConverter.toDto(post.getFirstVotingCount(), post.getSecondVotingCount());
    }

    private void sendNotification(int voteCount, User user) throws FirebaseMessagingException {
        Notification notification = null;
        if(voteCount == 10) {
            notification = Notification.builder()
                    .setTitle("asd")
                    .setBody("투표수가 10개가 되었어요!")
                    .build();
        } else if(voteCount == 50) {
            notification = Notification.builder()
                    .setTitle("asd")
                    .setBody("투표수가 50개가 되었어요.")
                    .build();
        } else if(voteCount == 100) {
            notification = Notification.builder()
                    .setTitle("asd")
                    .setBody("투표수가 100개가 되었어요.")
                    .build();
        }

        Message message = Message.builder()
                .setToken(user.getDeviceToken())
                .setNotification(notification)
                .build();

        firebaseMessaging.send(message);
    }
}
