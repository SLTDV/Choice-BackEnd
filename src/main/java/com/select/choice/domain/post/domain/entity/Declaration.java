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
public class Declaration extends BaseIdEntity {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Declaration(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
