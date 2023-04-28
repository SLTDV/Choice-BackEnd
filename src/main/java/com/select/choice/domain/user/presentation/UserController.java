package com.select.choice.domain.user.presentation;

import com.select.choice.domain.user.Service.*;
import com.select.choice.domain.user.presentation.data.dto.ChangeProfileImageDto;
import com.select.choice.domain.user.presentation.data.dto.HeaderDto;
import com.select.choice.domain.user.presentation.data.dto.MyPageDto;
import com.select.choice.domain.user.presentation.data.dto.NicknameDto;
import com.select.choice.domain.user.presentation.data.request.ChangeNicknameRequest;
import com.select.choice.domain.user.presentation.data.request.ChangeProfileImageRequest;
import com.select.choice.domain.user.presentation.data.response.GetMyPageResponse;
import com.select.choice.domain.user.presentation.data.response.HeaderResponse;
import com.select.choice.domain.user.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final ChangeNicknameService changeNicknameService;
    private final ChangeProfileImageService changeProfileImageService;
    private final GetHeaderService getHeaderService;
    private final GetMyPageService getMyPageService;
    private final WithdrawalService withdrawalService;
    private final UserConverter userConverter;

    /*
    기능: 회원탈퇴
    담당자: 노혁
     */
    @DeleteMapping
    public ResponseEntity<Void> withdrawal(){
        withdrawalService.withdrawal();
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
    기능: 닉네임 변경
    담당자: 노혁
     */
    @PatchMapping
    public ResponseEntity<Void> changeNickname(@RequestBody ChangeNicknameRequest changeNicknameRequest){
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
