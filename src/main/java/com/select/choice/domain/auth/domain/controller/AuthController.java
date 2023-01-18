package com.select.choice.domain.auth.domain.controller;

import com.select.choice.domain.auth.domain.data.dto.SignInDto;
import com.select.choice.domain.auth.domain.data.dto.SignUpDto;
import com.select.choice.domain.auth.domain.data.request.SignInRequest;
import com.select.choice.domain.auth.domain.data.request.SignUpRequest;
import com.select.choice.domain.auth.domain.data.response.TokenResponse;
import com.select.choice.domain.auth.domain.service.AuthService;
import com.select.choice.domain.auth.domain.util.AuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest){
        SignInDto signInDto = authConverter.toDto(signInRequest);
        TokenResponse response = authService.signIn(signInDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /*
    기능: 회원가입
    담당자: 노혁
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpRequest signUpRequest){
        SignUpDto signUpDto = authConverter.toDto(signUpRequest);
        authService.signUp(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    기능: 토큰 재발급
    담당자: 노혁
     */
    @PatchMapping
    public ResponseEntity<TokenResponse> refresh(@RequestHeader("RefreshToken") String refreshToken){
        TokenResponse response = authService.refresh(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
    기능: 로그아웃
    담당자: 노혁
     */
    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token){
        authService.logout(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
