package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.domain.repository.RefreshTokenRepository;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;
import com.select.choice.domain.auth.service.RefreshService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthConverter authConverter;

    @Override
    public TokenDto refresh(String refreshToken) {
        jwtTokenProvider.validateToken(refreshToken, JwtTokenProvider.TokenType.REFRESH_TOKEN);

        String email = jwtTokenProvider.getTokenSubject(refreshToken, JwtTokenProvider.TokenType.REFRESH_TOKEN);
        RefreshToken existingRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);

        String newAccessToken = jwtTokenProvider.generateAccessToken(email);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email);
        LocalDateTime newAccessExpiredTime = jwtTokenProvider.getAccessTokenExpiredTime();
        LocalDateTime newRefreshExpiredTime = jwtTokenProvider.getRefreshTokenExpiredTime();

        RefreshToken newRefreshTokenEntity = authConverter.toEntity(existingRefreshToken.getUserId(), newRefreshToken);

        refreshTokenRepository.save(newRefreshTokenEntity);

        return new TokenDto(
                newAccessToken,
                newRefreshToken,
                newAccessExpiredTime,
                newRefreshExpiredTime
        );
    }
}
