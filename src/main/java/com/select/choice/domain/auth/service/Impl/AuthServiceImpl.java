package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.data.dto.SignInDto;
import com.select.choice.domain.auth.data.dto.SignUpDto;
import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.response.TokenResponse;
import com.select.choice.domain.auth.service.AuthService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.auth.exception.*;
import com.select.choice.domain.user.data.entity.User;
import com.select.choice.domain.user.facade.UserFacade;
import com.select.choice.global.error.type.ErrorCode;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthConverter authConverter;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Override
    public TokenResponse signIn(SignInDto signInDto) {
        User user = userFacade.findUserByEmail(signInDto.getEmail());
        userFacade.checkPassword(user, signInDto.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        Long expiredAt = jwtTokenProvider.getExpiredTime(accessToken);

        TokenDto tokenDto = authConverter.toDto(accessToken, refreshToken, expiredAt);
        redisTemplate.opsForValue()
                .set("RefreshToken:" + user.getIdx(), tokenDto.getRefreshToken(),
                        jwtTokenProvider.getExpiredTime(tokenDto.getRefreshToken()), TimeUnit.MILLISECONDS);
        return authConverter.toResponse(tokenDto);
    }

    @Override
    public void signUp(SignUpDto signUpDto) {

        String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}";
        String pwPattern = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";

        if(!Pattern.matches(emailPattern,signUpDto.getEmail())){
            throw new EmailRegexpException(ErrorCode.EMAIL_REGEXP);
        }
        else if (!Pattern.matches(pwPattern, signUpDto.getPassword())) {
            throw new PasswordRegexpException(ErrorCode.PASSWORD_REGEXP);
        }
        else if (signUpDto.getNickname().length() > 6){
            throw new NicknameRegexpException(ErrorCode.NICKNAME_REGEXP);
        }
        else if(userFacade.existsByEmail(signUpDto.getEmail())) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
        else if (userFacade.existsByNickname(signUpDto.getNickname())) {
            throw new DuplicateNicknameException(ErrorCode.DUPLICATE_NICKNAME);
        }
        User user = authConverter.toEntity(signUpDto);
        userFacade.save(user);
    }

    @Transactional
    @Override
    public void logout(String token) {
        User user = userFacade.currentUser();
        String accessToken = token.substring(7);

        if (redisTemplate.opsForValue().get("RefreshToken:" + user.getIdx()) != null) {
            redisTemplate.delete("RefreshToken:" + user.getIdx());
        }

        Long expiration = jwtTokenProvider.getExpiredTime(accessToken);
        redisTemplate.opsForValue()
                .set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
    }

    @Transactional
    @Override
    public TokenResponse refresh(String refreshToken) {
        if(jwtTokenProvider.validateToken(refreshToken)){
            throw new ExpiredTokenException(ErrorCode.EXPIRED_TOKEN);
        }

        User user = userFacade.findUserByEmail(jwtTokenProvider.getUserPk(refreshToken));

        String redisRefreshToken = (String) redisTemplate.opsForValue().get("RefreshToken:" + user.getIdx());
        if(!Objects.equals(redisRefreshToken, refreshToken)){
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String newRefreshToken  = jwtTokenProvider.generateRefreshToken(user.getEmail());
        Long expiredAt = jwtTokenProvider.getExpiredTime(newAccessToken);

        TokenDto tokenDto = authConverter.toDto(newAccessToken, newRefreshToken, expiredAt);


        redisTemplate.opsForValue()
                .set("RefreshToken:" + user.getIdx(), newRefreshToken,
                        jwtTokenProvider.getExpiredTime(newRefreshToken), TimeUnit.MILLISECONDS);

        return authConverter.toResponse(tokenDto);
    }
}
