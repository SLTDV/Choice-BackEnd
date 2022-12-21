package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.presentation.dto.request.SignInRequest;
import com.select.choice.domain.auth.presentation.dto.response.SignInResponse;
import com.select.choice.domain.auth.service.SignInService;
import com.select.choice.domain.user.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        User user = userFacade.findUserByEmail(signInRequest.getEmail());
        userFacade.checkPassword(user, signInRequest.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(signInRequest.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(signInRequest.getEmail());

        user.updateRefreshToken(refreshToken);

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }
}
