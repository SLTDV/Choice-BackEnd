package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.data.response.RefreshTokenResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthConverter authConverter;

    @Transactional
    @Override
    public TokenDto signIn(SignInRequest signInRequest) {
        return authConverter.toTokenDto(signInRequest);
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

        user.updateRefreshToken(newRefreshToken);
        userFacade.saveRefreshToken(user);

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }
}
