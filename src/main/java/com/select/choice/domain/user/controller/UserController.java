package com.select.choice.domain.user.controller;

import com.select.choice.domain.user.Service.UserService;
import com.select.choice.domain.user.data.response.GetMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @DeleteMapping()
    public ResponseEntity<Void> delete(){
        userService.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<GetMyPageResponse> getMyPage(){
        GetMyPageResponse response = userService.getMyPage();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
