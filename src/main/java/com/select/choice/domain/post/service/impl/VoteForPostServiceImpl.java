package com.select.choice.domain.post.service.impl;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VoteForPostDto voteForPost(VoteOptionDto addCountDto, Long postIdx) {
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

        return postConverter.toDto(post.getFirstVotingCount(), post.getSecondVotingCount());
    }
}
