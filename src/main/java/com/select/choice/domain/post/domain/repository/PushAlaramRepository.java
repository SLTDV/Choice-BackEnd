package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.PushAlaram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PushAlaramRepository extends JpaRepository<PushAlaram, Long> {
    Optional<PushAlaram> findByPost(Post post);
}
