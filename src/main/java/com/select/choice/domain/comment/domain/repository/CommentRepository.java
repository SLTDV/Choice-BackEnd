package com.select.choice.domain.comment.domain.repository;

import com.select.choice.domain.comment.domain.entity.Comment;
import com.select.choice.domain.post.domain.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdx(Long postIdx, Pageable pageable);
    int countByPost(Post post);
}
