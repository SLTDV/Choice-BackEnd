package com.select.choice.domain.post.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostVotingState extends BaseIdEntity{
    private int vote;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public PostVotingState(int vote, User user, Post post){
        this.vote = vote;
        this.user = user;
        this.post = post;
    }

    public void updateVote(int vote) {
        this.vote = vote;
    }
}
