package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.VotingPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingPostRepository extends JpaRepository<VotingPost, Long> {
    boolean existsByUserAndPost();
}
