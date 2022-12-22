package com.select.choice.domain.auth.util;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthConverterImpl implements AuthConverter{

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
