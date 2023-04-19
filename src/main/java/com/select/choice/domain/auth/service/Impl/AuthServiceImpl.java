package com.select.choice.domain.auth.service.Impl;

import com.select.choice.domain.auth.domain.entity.AuthCode;
import com.select.choice.domain.auth.domain.entity.Authentication;
import com.select.choice.domain.auth.domain.repository.AuthCodeRepository;
import com.select.choice.domain.auth.domain.repository.AuthenticationRepository;
import com.select.choice.domain.auth.presentation.data.dto.*;
import com.select.choice.domain.auth.domain.entity.RefreshToken;
import com.select.choice.domain.auth.domain.repository.RefreshTokenRepository;
import com.select.choice.domain.auth.properties.CoolSMSProperties;
import com.select.choice.domain.auth.service.AuthService;
import com.select.choice.domain.auth.util.AuthConverter;
import com.select.choice.domain.auth.exception.*;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import com.select.choice.global.redis.RedisUtil;
import com.select.choice.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserUtil userUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthConverter authConverter;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;
    private final CoolSMSProperties coolSMSProperties;
    private final AuthCodeRepository authCodeRepository;
    private final AuthenticationRepository authenticationRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TokenDto signIn(SignInDto signInDto) {
        User user = userUtil.findUserByPhoneNumber(signInDto.getPhoneNumber());
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

    @Override
    public void signUp(SignUpDto signUpDto) {
        if (userUtil.existsByNickname(signUpDto.getNickname())) {
            throw new DuplicateNicknameException(ErrorCode.DUPLICATE_NICKNAME);
        } else if (signUpDto.getNickname().startsWith(" ")) {
            throw new NicknameRegexpException(ErrorCode.NICKNAME_REGEXP);
        }

        User user = authConverter.toEntity(signUpDto, signUpDto.getProfileImgUrl().isPresent());
        userUtil.save(user);
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
    public void sendSMS(String phoneNumber) throws CoolsmsException {
        if(userUtil.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicatePhoneNumberException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        }

        Message coolsms = new Message(coolSMSProperties.getApiKey(), coolSMSProperties.getApiSecret());

        Random rand  = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01065657236");    // 발신전화번호
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");

        coolsms.send(params);

        authCodeRepository.save(authConverter.toEntity(numStr.toString(), phoneNumber));
    }

    @Override
    public void checkAuthCode(String phoneNumber, String authCode) {
        AuthCode entity = authCodeRepository.findById(phoneNumber)
                .orElseThrow(() -> new AuthCodeNotFoundException(ErrorCode.AUTH_CODE_NOT_FOUND));

        if(!authCode.equals(entity.getCode())) {
            throw new InValidAuthCodeException(ErrorCode.INVALID_AUTH_CODE);
        }

        Authentication authentication = authConverter.toEntity(phoneNumber);
        authenticationRepository.save(authentication);
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
