package com.select.choice.domain.auth.util;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.data.response.TokenResponse;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthConverterImpl implements AuthConverter{
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

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
    public TokenDto toTokenDto(SignInRequest request) {
        User user = userFacade.findUserByEmail(request.getEmail());
        userFacade.checkPassword(user, request.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        Long expiredAt = jwtTokenProvider.getExpiredTime(accessToken);

        user.updateRefreshToken(refreshToken);

        return new TokenDto(accessToken,refreshToken,expiredAt);
    }

    @Override
    public TokenDto toTokenDto(SignUpRequest request) {
        return null;
    }
}
