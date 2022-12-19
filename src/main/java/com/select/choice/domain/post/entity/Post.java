package com.select.choice.domain.post.entity;


import com.select.choice.domain.user.entity.User;
import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseIdEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String thumbnail;
    @Column(nullable = false)
    private String votingOption1;
    @Column(nullable = false)
    private String votingOption2;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Post(String title, String content, String thumbnail, String votingOption1, String votingOption2){
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.votingOption1 = votingOption1;
        this.votingOption2 = votingOption2;

    }
}