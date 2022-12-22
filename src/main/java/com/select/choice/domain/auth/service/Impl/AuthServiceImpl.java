package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.exception.DuplicateEmailException;
import com.select.choice.domain.auth.exception.ExpiredTokenException;
import com.select.choice.domain.auth.exception.InvalidTokenException;
import com.select.choice.domain.auth.service.AuthService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthConverter authConverter;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Override
    public TokenDto signIn(SignInRequest signInRequest) {
        User user = userFacade.findUserByEmail(signInRequest.getEmail());
        userFacade.checkPassword(user, signInRequest.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        Long expiredAt = jwtTokenProvider.getExpiredTime(accessToken);

        TokenDto tokenDto = authConverter.toTokenDto(accessToken, refreshToken, expiredAt);
        redisTemplate.opsForValue()
                .set("RefreshToken:" + user.getIdx(), tokenDto.getRefreshToken(),
                        jwtTokenProvider.getExpiredTime(tokenDto.getRefreshToken()), TimeUnit.MILLISECONDS);
        return tokenDto;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if(userFacade.existsByEmail(signUpRequest.getEmail())) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
        userFacade.save(signUpRequest);
    }

    @Transactional
    @Override
    public TokenDto refresh(String refreshToken) {
        if(jwtTokenProvider.validateToken(refreshToken)){
            throw new ExpiredTokenException(ErrorCode.EXPIRED_TOKEN);
        }

        User user = userFacade.findUserByEmail(jwtTokenProvider.getUserPk(refreshToken));

        String redisRefreshToken = (String) redisTemplate.opsForValue().get("refreshToken:" + user.getIdx());
        if(Objects.equals(redisRefreshToken, refreshToken)){
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String newRefreshToken  = jwtTokenProvider.generateRefreshToken(user.getEmail());
        Long expiredAt = jwtTokenProvider.getExpiredTime(newAccessToken);

        redisTemplate.opsForValue()
                .set("RefreshToken:" + user.getIdx(), refreshToken,
                        jwtTokenProvider.getExpiredTime(newRefreshToken), TimeUnit.MILLISECONDS);

        return new TokenDto(newAccessToken, newRefreshToken, expiredAt);
    }
}
