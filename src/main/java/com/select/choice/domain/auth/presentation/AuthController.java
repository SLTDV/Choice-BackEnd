package com.select.choice.domain.auth.presentation;

import com.select.choice.domain.auth.presentation.data.dto.*;
import com.select.choice.domain.auth.presentation.data.request.SendPhoneNumberRequest;
import com.select.choice.domain.auth.presentation.data.request.SignInRequest;
import com.select.choice.domain.auth.presentation.data.request.SignUpRequest;
import com.select.choice.domain.auth.presentation.data.request.SignupDuplicationCheckRequest;
import com.select.choice.domain.auth.presentation.data.response.TokenResponse;
import com.select.choice.domain.auth.service.AuthService;
import com.select.choice.domain.auth.util.AuthConverter;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthConverter authConverter;

    /*
    기능: 로그인
    담당자: 노혁
     */
    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        SignInDto signInDto = authConverter.toDto(signInRequest);
        TokenDto tokenDto = authService.signIn(signInDto);
        TokenResponse tokenResponse = authConverter.toResponse(tokenDto);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);

    }

    /*
    기능: 회원가입
    담당자: 노혁
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        SignUpDto signUpDto = authConverter.toDto(signUpRequest);
        authService.signUp(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    기능: 회원가입 이메일 중복검사
    담당자: 노혁
     */
    @PostMapping("/duplication")
    public ResponseEntity<Void> SignupDuplicationCheck(@RequestBody SignupDuplicationCheckRequest signupDuplicationCheckRequest) {
        SignupDuplicationCheckDto signupDuplicationCheckDto = authConverter.toDto(signupDuplicationCheckRequest);
        authService.signupDuplicationCheck(signupDuplicationCheckDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    기능: 토큰 재발급
    담당자: 노혁
     */
    @PatchMapping
    public ResponseEntity<TokenResponse> refresh(@RequestHeader("RefreshToken") String refreshToken) {
        TokenDto tokenDto = authService.refresh(refreshToken);
        TokenResponse tokenResponse = authConverter.toResponse(tokenDto);
        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }

    /*
    기능: 로그아웃
    담당자: 노혁
     */
    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 휴대전화로 인증번호 전송
    담당자: 노혁
     */
    @PostMapping("/phone")
    public ResponseEntity<Void> sendSMS(@RequestParam("phoneNumber") String phoneNumber) throws CoolsmsException {
        authService.sendSMS(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    기능: 인즌번호 확인
    담당자: 노혁
     */
    @GetMapping("/phone")
    public ResponseEntity<Void> checkAuthCode(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("authCode") String authCode) {
        authService.checkAuthCode(phoneNumber, authCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
