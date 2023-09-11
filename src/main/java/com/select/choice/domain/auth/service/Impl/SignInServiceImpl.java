package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.domain.repository.RefreshTokenRepository;
import com.select.choice.domain.auth.presentation.data.dto.SignInDto;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;
import com.select.choice.domain.auth.service.SignInService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final UserUtil userUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthConverter authConverter;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public TokenDto signIn(SignInDto signInDto) {
        User user = userUtil.findUserByPhoneNumber(signInDto.getPhoneNumber());
        if(signInDto.getDeviceToken().isPresent()){
            System.out.println(signInDto.getDeviceToken().toString());
            user.updateFCMToken(signInDto.getDeviceToken().get());
        }

        userUtil.checkPassword(user, signInDto.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getPhoneNumber());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getPhoneNumber());
        LocalDateTime accessExpiredTime = jwtTokenProvider.getAccessTokenExpiredTime();
        LocalDateTime refreshExpiredTime = jwtTokenProvider.getRefreshTokenExpiredTime();

        RefreshToken refresh = authConverter.toEntity(user.getIdx(), refreshToken);
        refreshTokenRepository.save(refresh);

        return new TokenDto(
                accessToken,
                refreshToken,
                accessExpiredTime,
                refreshExpiredTime
        );
    }
}
