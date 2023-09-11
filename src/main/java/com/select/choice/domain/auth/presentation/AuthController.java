package com.select.choice.domain.auth.presentation;

import com.select.choice.domain.auth.presentation.data.dto.*;
import com.select.choice.domain.auth.presentation.data.request.ChangePasswordRequest;
import com.select.choice.domain.auth.presentation.data.request.SignInRequest;
import com.select.choice.domain.auth.presentation.data.request.SignUpRequest;
import com.select.choice.domain.auth.presentation.data.response.TokenResponse;
import com.select.choice.domain.auth.service.*;
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
    private final CheckAuthCodeService checkAuthCodeService;
    private final LogoutService logoutService;
    private final RefreshService refreshService;
    private final SendSmsService sendSmsService;
    private final SignInService signInService;
    private final SignUpService signUpService;
    private final AuthConverter authConverter;
    private final ChangePasswordService changePasswordService;

    /*
    기능: 로그인
    담당자: 노혁
     */
    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        System.out.println("??");
        System.out.println(signInRequest.getDeviceToken().toString());
        SignInDto signInDto = authConverter.toDto(signInRequest);
        TokenDto tokenDto = signInService.signIn(signInDto);
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
        signUpService.signUp(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    기능: 토큰 재발급
    담당자: 노혁
     */
    @PatchMapping
    public ResponseEntity<TokenResponse> refresh(@RequestHeader("RefreshToken") String refreshToken) {
        TokenDto tokenDto = refreshService.refresh(refreshToken);
        TokenResponse tokenResponse = authConverter.toResponse(tokenDto);
        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }

    /*
    기능: 로그아웃
    담당자: 노혁
     */
    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        logoutService.logout(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 휴대전화로 인증번호 전송
    담당자: 노혁
     */
    @PostMapping("/phone")
    public ResponseEntity<Void> sendSMS(@RequestParam("phoneNumber") String phoneNumber) throws CoolsmsException {
        sendSmsService.sendSMS(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    기능: 인즌번호 확인
    담당자: 노혁
     */
    @GetMapping("/phone")
    public ResponseEntity<Void> checkAuthCode(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("authCode") String authCode) {
        checkAuthCodeService.checkAuthCode(phoneNumber, authCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    기능: 비밀번호 변경
    담당자: 노혁
     */
    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        changePasswordService.change(authConverter.toDto(request));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 휴대전화로 인증번호 전송 PasswordVersion
    담당자: 노혁
    */
    @PostMapping("/phone/password")
    public ResponseEntity<Void> send(@RequestParam("phoneNumber") String phoneNumber) throws CoolsmsException {
        sendSmsService.send(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
