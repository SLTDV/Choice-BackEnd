package com.select.choice.domain.comment.domain.repository;

import com.select.choice.domain.comment.domain.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
   List<Comment> findAll(Long postIdx);
}
