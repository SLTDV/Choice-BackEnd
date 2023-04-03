package com.select.choice.domain.post.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private int firstVotingCount;
    private int secondVotingCount;
    private LocalDate createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PostVotingStatus> postVotingStatuses;

    @Builder
    public Post(String title, String content, String firstVotingOption, String secondVotingOption, String firstImageUrl, String secondImageUrl, int firstVotingCount, int secondVotingCount, User user, LocalDate createdAt) {
        this.title = title;
        this.content = content;
        this.firstVotingOption = firstVotingOption;
        this.secondVotingOption = secondVotingOption;
        this.firstImageUrl = firstImageUrl;
        this.secondImageUrl = secondImageUrl;
        this.firstVotingCount = firstVotingCount;
        this.secondVotingCount = secondVotingCount;
        this.user = user;
        this.createdAt = createdAt;
    }


    public void updateVotingCount(int voting, int choiceOption) {
        if(voting != 0){
            if(choiceOption == 1){
                ++this.firstVotingCount;
                --this.secondVotingCount;
            } else {
                ++this.secondVotingCount;
                --this.firstVotingCount;
            }
        } else{
            if(choiceOption == 1) ++this.firstVotingCount;
            else ++this.secondVotingCount;
        }
    }
}