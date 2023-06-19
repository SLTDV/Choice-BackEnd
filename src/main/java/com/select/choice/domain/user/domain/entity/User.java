package com.select.choice.domain.user.domain.entity;

import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseIdEntity {
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;
    private String profileImageUrl;

    @OneToMany(mappedBy = "blockingUser")
    private List<BlockedUser> blockedUsers;

    @Builder
    public User(String phoneNumber, String password, String nickname, String profileImageUrl) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImage(String image) {
        this.profileImageUrl = image;
    }

    public void changePassword(String password) { this.password = password; }
}
