package com.select.choice.domain.auth.util;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.data.response.SignInResponse;

public interface AuthConverter {
    SignInResponse toResponse(TokenDto dto);
    TokenDto toTokenDto(SignInRequest request);
    TokenDto toTokenDto(SignUpRequest request);
}
