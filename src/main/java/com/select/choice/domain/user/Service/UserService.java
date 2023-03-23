package com.select.choice.domain.user.Service;

import com.select.choice.domain.user.presentation.data.dto.ChangeProfileImageDto;
import com.select.choice.domain.user.presentation.data.dto.HeaderDto;
import com.select.choice.domain.user.presentation.data.dto.MyPageDto;
import com.select.choice.domain.user.presentation.data.dto.NicknameDto;

public interface UserService {
    void delete();
    MyPageDto getMyPage();
    void changeNickname(NicknameDto nicknameDto);
    void changeProfileImage(ChangeProfileImageDto changeProfileImageDto);
    HeaderDto getHeader();
}
