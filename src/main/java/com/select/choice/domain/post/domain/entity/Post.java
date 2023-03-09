package com.select.choice.domain.post.domain.entity;


import com.select.choice.domain.user.data.entity.User;
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
    private String firstImageUrl;
    @Column(nullable = false)
    private String secondImageUrl;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String firstVotingOption;
    @Column(nullable = false)
    private String secondVotingOption;
    private Integer firstVotingCount;
    private Integer secondVotingCount;

    private boolean IsVoting;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Post(String title, String content, String firstVotingOption, String secondVotingOption, String firstImageUrl, String secondImageUrl, Integer firstVotingCount, Integer secondVotingCount, User user) {
        this.title = title;
        this.content = content;
        this.firstVotingOption = firstVotingOption;
        this.secondVotingOption = secondVotingOption;
        this.firstImageUrl = firstImageUrl;
        this.secondImageUrl = secondImageUrl;
        this.firstVotingCount = firstVotingCount;
        this.secondVotingCount = secondVotingCount;
        this.user = user;
    }

    public void updateFirstVotingCount(){
        ++this.firstVotingCount;
    }

    public void updateSecondVotingCount(){
        ++this.secondVotingCount;
    }

    public void updateIsVoting(){
        this.IsVoting = true;
    }
}