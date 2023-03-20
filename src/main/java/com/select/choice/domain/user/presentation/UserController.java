package com.select.choice.domain.user.presentation;

import com.select.choice.domain.user.Service.UserService;
import com.select.choice.domain.user.presentation.data.dto.ChangeProfileImageDto;
import com.select.choice.domain.user.presentation.data.dto.MyPageDto;
import com.select.choice.domain.user.presentation.data.dto.NicknameDto;
import com.select.choice.domain.user.presentation.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.presentation.data.request.ChangeProfileImageRequest;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;
import com.select.choice.domain.user.util.UserConverter;
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

    /*
    기능: 회원탈퇴
    담당자: 노혁
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(){
        userService.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 마이페이지
    담당자: 노혁
     */
    @GetMapping
    public ResponseEntity<GetMyPageResponse> getMyPage(){
        MyPageDto myPageDto = userService.getMyPage();
        GetMyPageResponse myPageResponse = userConverter.toResponse(myPageDto);
        return new ResponseEntity<>(myPageResponse, HttpStatus.OK);
    }

    /*
    기능: 닉네임 변경
    담당자: 노혁
     */
    @PatchMapping
    public ResponseEntity<Void> changeNickname(@RequestBody ChangeNicknameRequest changeNicknameRequest){
        NicknameDto nicknameDto = userConverter.toDto(changeNicknameRequest);
        userService.changeNickname(nicknameDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 프로필 이미지 변경
    담당자: 노혁
     */
    @PatchMapping("/image")
    public ResponseEntity<Void> changeProfileImage(@RequestBody ChangeProfileImageRequest changeProfileImageRequest){
        ChangeProfileImageDto changeProfileImageDto = userConverter.toDto(changeProfileImageRequest);
        userService.changeProfileImage(changeProfileImageDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
