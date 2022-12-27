package com.select.choice.domain.user.domain.Service;

import com.select.choice.domain.user.domain.data.dto.NicknameDto;
import com.select.choice.domain.user.domain.data.response.GetMyPageResponse;

public interface UserService {
    void delete();
    GetMyPageResponse getMyPage();

    void changeNickname(NicknameDto nicknameDto);
}
