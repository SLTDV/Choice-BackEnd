package com.select.choice.domain.post.entity;


import com.select.choice.global.common.entity.BaseIdEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
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
    private user:User


}
