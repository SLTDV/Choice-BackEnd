package com.select.choice.domain.user.entity;

import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseIdEntity {
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    @Builder
    public User(String id,String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

}
