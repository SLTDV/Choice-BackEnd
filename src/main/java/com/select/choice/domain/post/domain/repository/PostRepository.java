package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
