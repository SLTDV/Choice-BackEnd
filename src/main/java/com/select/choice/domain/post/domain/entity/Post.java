package com.select.choice.domain.post.domain.entity;


import com.select.choice.domain.user.domain.entity.User;
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
    private int commentCount;

    private boolean voting;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Post(String title, String content, String firstVotingOption, String secondVotingOption, String firstImageUrl, String secondImageUrl, Integer firstVotingCount, Integer secondVotingCount, User user, int commentCount) {
        this.title = title;
        this.content = content;
        this.firstVotingOption = firstVotingOption;
        this.secondVotingOption = secondVotingOption;
        this.firstImageUrl = firstImageUrl;
        this.secondImageUrl = secondImageUrl;
        this.firstVotingCount = firstVotingCount;
        this.secondVotingCount = secondVotingCount;
        this.user = user;
        this.commentCount = commentCount;
    }

    public void updateVotingCount(boolean voting, int choiceOption) {
        if(voting){
            if(choiceOption == 0){
                ++this.firstVotingCount;
                --this.secondVotingCount;
            } else {
                ++this.secondVotingCount;
                --this.firstVotingCount;
            }
        } else{
            if(choiceOption == 0) ++this.firstVotingCount;
            else ++this.secondVotingCount;
        }
    }

    public void updateIsVoting(){
        this.voting = true;
    }

    public void updateCount() {
        ++this.commentCount;
    }

    public void minusCount() {
        --this.commentCount;
    }
}