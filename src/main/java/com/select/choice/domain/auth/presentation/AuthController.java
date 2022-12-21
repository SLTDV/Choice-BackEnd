package com.select.choice.domain.auth.presentation;

import com.select.choice.domain.auth.presentation.dto.request.SignInRequest;
import com.select.choice.domain.auth.presentation.dto.request.SignUpRequest;
import com.select.choice.domain.auth.presentation.dto.response.RefreshTokenResponse;
import com.select.choice.domain.auth.presentation.dto.response.SignInResponse;
import com.select.choice.domain.auth.service.RefreshTokenService;
import com.select.choice.domain.auth.service.SignInService;
import com.select.choice.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final SignInService signInService;
    private final SignUpService signUpService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        return new ResponseEntity<>(signInService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest){
        signUpService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestHeader("RefreshToken") String refreshToken){
        return new ResponseEntity<>(refreshTokenService.refresh(refreshToken), HttpStatus.CREATED);
    }
}
