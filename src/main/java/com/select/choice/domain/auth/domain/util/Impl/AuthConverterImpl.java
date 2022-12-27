package com.select.choice.domain.auth.domain.util.Impl;

import com.select.choice.domain.auth.domain.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.data.response.TokenResponse;
import com.select.choice.domain.auth.domain.util.AuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthConverterImpl implements AuthConverter {

    @Override
    public TokenResponse toResponse(TokenDto dto) {
        return new TokenResponse(
                dto.getAccessToken(),
                dto.getRefreshToken(),
                dto.getExpiredAt()
        );
    }

    @Transactional
    @Override
    public TokenDto toTokenDto(String accessToken, String refreshToken, Long expiredAt) {
        return new TokenDto(accessToken,refreshToken,expiredAt);
    }
}
