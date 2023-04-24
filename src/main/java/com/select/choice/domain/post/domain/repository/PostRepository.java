package com.select.choice.domain.post.domain.repository;

import com.select.choice.domain.post.domain.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("select p from Post p order by (p.firstVotingCount + p.secondVotingCount) desc")
    List<Post> getBestPostList(Pageable pageable);
    List<Post> findAllByCreatedAtContaining(String today);
    @Query("select p from Post p order by p.idx desc ")
    List<Post> getAllPostList(Pageable pageable);
    List<Post> findAllByUserIdx(Long idx);
}
