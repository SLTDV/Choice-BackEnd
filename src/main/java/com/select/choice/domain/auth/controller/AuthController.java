package com.select.choice.domain.auth.controller;

import com.select.choice.domain.auth.data.dto.TokenDto;
import com.select.choice.domain.auth.data.request.SignInRequest;
import com.select.choice.domain.auth.data.request.SignUpRequest;
import com.select.choice.domain.auth.data.response.RefreshTokenResponse;
import com.select.choice.domain.auth.data.response.SignInResponse;
import com.select.choice.domain.auth.service.AuthService;
import com.select.choice.domain.auth.util.AuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthConverter authConverter;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        TokenDto tokenDto = authService.signIn(signInRequest);
        SignInResponse response = authConverter.toResponse(tokenDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest){
        authService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestHeader("RefreshToken") String refreshToken){
        return new ResponseEntity<>(authService.refresh(refreshToken), HttpStatus.CREATED);
    }
}
