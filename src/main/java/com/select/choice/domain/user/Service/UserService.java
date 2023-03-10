package com.select.choice.domain.user.Service;

import com.select.choice.domain.user.presentation.data.dto.NicknameDto;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;

public interface UserService {
    void delete();
    GetMyPageResponse getMyPage();

    void changeNickname(NicknameDto nicknameDto);
}
