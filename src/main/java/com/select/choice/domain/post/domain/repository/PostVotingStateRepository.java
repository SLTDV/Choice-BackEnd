package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.PostVotingState;
import com.select.choice.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostVotingStateRepository extends JpaRepository<PostVotingState, Long> {
    PostVotingState findPostVotingStatusByUserAndPost(User user, Post post);
    Optional<PostVotingState> findByUserAndPost(User user, Post post);
    boolean existsByUserAndPost(User user, Post post);
}
