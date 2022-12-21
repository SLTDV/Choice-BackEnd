package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.exception.ExpiredTokenException;
import com.select.choice.domain.auth.exception.InvalidTokenException;
import com.select.choice.domain.auth.presentation.dto.response.RefreshTokenResponse;
import com.select.choice.domain.auth.service.RefreshTokenService;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RefreshTokenResponse refresh(String refreshToken) {
        if(jwtTokenProvider.validateToken(refreshToken)){
            throw new ExpiredTokenException(ErrorCode.EXPIRED_TOKEN);
        }

        User user = userFacade.findUserByEmail(jwtTokenProvider.getUserPk(refreshToken));

        if(!user.getRefreshToken().equals(refreshToken)){
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String newRefreshToken  = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }
}
