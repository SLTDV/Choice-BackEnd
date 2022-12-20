package com.select.choice.domain.auth.presentation;

import com.select.choice.domain.auth.presentation.request.SignInRequest;
import com.select.choice.domain.auth.presentation.request.SignUpRequest;
import com.select.choice.domain.auth.presentation.response.SignInResponse;
import com.select.choice.domain.auth.service.SignInService;
import com.select.choice.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final SignInService signInService;
    private final SignUpService signUpService;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        return new ResponseEntity<>(signInService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest){
        signUpService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
