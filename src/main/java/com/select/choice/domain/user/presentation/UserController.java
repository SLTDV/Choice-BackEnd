package com.select.choice.domain.user.presentation;

import com.select.choice.domain.user.service.*;
import com.select.choice.domain.user.presentation.data.dto.*;
import com.select.choice.domain.user.presentation.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.presentation.data.request.ChangeProfileImageRequest;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;
import com.select.choice.domain.user.presentation.data.response.HeaderResponse;
import com.select.choice.domain.user.presentation.data.response.WebMyPageResponse;
import com.select.choice.domain.user.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final WithdrawalUserService withdrawalUserService;
    private final GetMyPageService getMyPageService;
    private final GetHeaderService getHeaderService;
    private final ChangeProfileImageService changeProfileImageService;
    private final ChangeNicknameService changeNicknameService;
    private final GetWebMyPageService getWebMypageService;
    private final UserConverter userConverter;

    /*
    기능: 회원탈퇴
    담당자: 노혁
     */
    @DeleteMapping
    public ResponseEntity<Void> withdrawal(){
        withdrawalUserService.withdrawal();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 마이페이지
    담당자: 노혁
     */
    @GetMapping
    public ResponseEntity<GetMyPageResponse> getMyPage(){
        MyPageDto myPageDto = getMyPageService.getMyPage();
        GetMyPageResponse myPageResponse = userConverter.toResponse(myPageDto);
        return new ResponseEntity<>(myPageResponse, HttpStatus.OK);
    }

    /*
    기능: 마이페이지 WEB version
    담당자: 노혁
     */
    @GetMapping("/my")
    public ResponseEntity<WebMyPageResponse> getWebMyPage() {
        System.out.println("???");
        WebMyPageDto myPageDto = getWebMypageService.getMyPage();
        WebMyPageResponse myPageResponse = userConverter.toResponse(myPageDto);
        return new ResponseEntity<>(myPageResponse, HttpStatus.OK);
    }

    /*
    기능: 닉네임 변경
    담당자: 노혁
     */
    @PatchMapping
    public ResponseEntity<Void> changeNickname(@RequestBody @Valid ChangeNicknameRequest changeNicknameRequest){
        NicknameDto nicknameDto = userConverter.toDto(changeNicknameRequest);
        changeNicknameService.changeNickname(nicknameDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 프로필 이미지 변경
    담당자: 노혁
     */
    @PatchMapping("/image")
    public ResponseEntity<Void> changeProfileImage(@RequestBody ChangeProfileImageRequest changeProfileImageRequest){
        ChangeProfileImageDto changeProfileImageDto = userConverter.toDto(changeProfileImageRequest);
        changeProfileImageService.changeProfileImage(changeProfileImageDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: header
    담당자: 노혁
     */
    @GetMapping("/header")
    public ResponseEntity<HeaderResponse> getHeader(){
        HeaderDto headerDto = getHeaderService.getHeader();
        HeaderResponse headerResponse = userConverter.toResponse(headerDto);
        return new ResponseEntity<>(headerResponse, HttpStatus.OK);
    }
}
