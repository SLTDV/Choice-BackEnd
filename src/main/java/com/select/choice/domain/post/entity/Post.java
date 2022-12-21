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
    private String firstVotingOption;
    @Column(nullable = false)
    private String secondVotingOption;

    @Column
    private int firstVotingCount;

    @Column
    private int secondVotingCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Post(String title, String content, String thumbnail, String firstVotingOption, String secondVotingOption,int firstVotingCount, int secondVotingCount){
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.firstVotingOption = firstVotingOption;
        this.secondVotingOption = secondVotingOption;
        this.firstVotingCount = firstVotingCount;
        this.secondVotingCount = secondVotingCount;

    }
}