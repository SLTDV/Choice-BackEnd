package com.select.choice.domain.post.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PushAlaram extends BaseIdEntity {
    @Column(nullable = false)
    private boolean tenPush;
    @Column(nullable = false)
    private boolean fiftyPush;
    @Column(nullable = false)
    private boolean oneHundredPush;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Builder
    public PushAlaram(boolean tenPush, boolean fiftyPush, boolean oneHundredPush, Post post) {
        this.tenPush = tenPush;
        this.fiftyPush = fiftyPush;
        this.oneHundredPush = oneHundredPush;
        this.post = post;
    }

    public void updateTenPush() { this.tenPush = true; }
    public void updateFiftyPush() { this.fiftyPush = true; }
    public void updateOneHundredPush() { this.oneHundredPush = true; }
}
