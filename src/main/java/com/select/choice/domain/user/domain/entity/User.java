package com.select.choice.domain.user.domain.entity;

import com.select.choice.global.common.entity.BaseIdEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

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
    private String fcmToken;

    @OneToMany(mappedBy = "blockingUser")
    private List<BlockedUser> blockedUsers;

    @OneToMany(mappedBy = "blockedUser")
    private List<BlockedUser> blockingUsers;

    @Builder
    public User(String phoneNumber, String password, String nickname, String profileImageUrl, String fcmToken) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.fcmToken = fcmToken;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImage(String image) {
        this.profileImageUrl = image;
    }

    public void changePassword(String password) { this.password = password; }

    public void updateFCMToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
