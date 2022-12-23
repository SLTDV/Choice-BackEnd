package com.select.choice.domain.comment.entity;

import com.select.choice.domain.post.domain.data.entity.Post;
import com.select.choice.domain.user.entity.User;
import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseIdEntity {
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post__id", nullable = false)
    private Post post;

    @Builder
    public Comment(String content, User user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
    }
}
