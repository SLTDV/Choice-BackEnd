package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.VotingPost;
import com.select.choice.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingPostRepository extends JpaRepository<VotingPost, Long> {
    boolean existsByUserAndPost(User user, Post post);
    VotingPost findByUserAndPost(User user, Post post);
}
