package com.select.choice.domain.user.Service;

import com.select.choice.domain.user.data.response.GetMyPageResponse;

public interface UserService {
    void delete();
    GetMyPageResponse getMyPage();
}
