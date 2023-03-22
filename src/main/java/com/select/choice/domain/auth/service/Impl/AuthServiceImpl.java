package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.presentation.data.dto.SignInDto;
import com.select.choice.domain.auth.presentation.data.dto.SignUpDto;
import com.select.choice.domain.auth.presentation.data.dto.TokenDto;
import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.domain.repository.RefreshTokenRepository;
import com.select.choice.domain.auth.service.AuthService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.auth.exception.*;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.entity.PostVotingStatus;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.domain.repository.PostVotingStatusRepository;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import com.select.choice.global.redis.RedisUtil;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserUtil userUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthConverter authConverter;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;
    private final PostVotingStatusRepository postVotingStatusRepository;
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TokenDto signIn(SignInDto signInDto) {
        User user = userUtil.findUserByEmail(signInDto.getEmail());
        userUtil.checkPassword(user, signInDto.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
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

    @Override
    public void signUp(SignUpDto signUpDto) {
        if(userUtil.existsByEmail(signUpDto.getEmail())) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
        else if (userUtil.existsByNickname(signUpDto.getNickname())) {
            throw new DuplicateNicknameException(ErrorCode.DUPLICATE_NICKNAME);
        }

        User user = authConverter.toEntity(signUpDto);
        userUtil.save(user);

        List<Post> postList = postRepository.findAll();
        for(Post post: postList) {
            PostVotingStatus postVotingStatus = postConverter.toEntity(user, post);
            postVotingStatusRepository.save(postVotingStatus);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(String token) {
        User user = userUtil.currentUser();
        String accessToken = token.substring(7);

        refreshTokenRepository.deleteById(user.getIdx());

        Long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "access_token", expiration);

    }

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
