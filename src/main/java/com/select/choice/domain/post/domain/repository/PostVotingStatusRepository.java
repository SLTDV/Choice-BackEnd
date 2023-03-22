package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.PostVotingStatus;
import com.select.choice.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVotingStatusRepository extends JpaRepository<PostVotingStatus, Long> {
    PostVotingStatus findByUserAndPost(User user, Post post);
}
