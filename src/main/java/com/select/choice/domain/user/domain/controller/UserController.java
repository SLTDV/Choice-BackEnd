package com.select.choice.domain.user.domain.controller;

import com.select.choice.domain.user.domain.Service.UserService;
import com.select.choice.domain.user.domain.data.dto.NicknameDto;
import com.select.choice.domain.user.domain.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.domain.data.response.GetMyPageResponse;
import com.select.choice.domain.user.domain.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

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

    @PatchMapping()
    public ResponseEntity<Void> changeNickname(@RequestBody ChangeNicknameRequest changeNicknameRequest){
        NicknameDto nicknameDto = userConverter.toNicknameDto(changeNicknameRequest);
        userService.changeNickname(nicknameDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}