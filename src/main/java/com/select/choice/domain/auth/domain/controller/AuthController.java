package com.select.choice.domain.auth.domain.controller;

import com.select.choice.domain.auth.domain.data.dto.TokenDto;
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

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest){
        TokenDto tokenDto = authService.signIn(signInRequest);
        TokenResponse response = authConverter.toResponse(tokenDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpRequest signUpRequest){
        authService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<TokenResponse> refresh(@RequestHeader("RefreshToken") String refreshToken){
        TokenDto tokenDto = authService.refresh(refreshToken);
        TokenResponse response = authConverter.toResponse(tokenDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        authService.logout(token);
        return new ResponseEntity<>("success",HttpStatus.NO_CONTENT);
    }
}