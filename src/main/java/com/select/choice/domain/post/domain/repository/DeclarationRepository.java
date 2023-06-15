package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.Declaration;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    boolean existsByPostAndUser(Post post, User user);
    boolean existsByPost(Post post);
    Declaration findByPost(Post post);
}
