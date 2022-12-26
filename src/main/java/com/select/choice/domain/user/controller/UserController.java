package com.select.choice.domain.user.controller;

import com.select.choice.domain.user.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
