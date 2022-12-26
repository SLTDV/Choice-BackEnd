package com.select.choice.domain.comment.repository;

import com.select.choice.domain.comment.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
