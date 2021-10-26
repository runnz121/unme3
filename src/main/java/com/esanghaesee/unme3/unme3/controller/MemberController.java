package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import com.esanghaesee.unme3.unme3.service.ImageService;
import com.esanghaesee.unme3.unme3.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ImageService imageService;


    //회원정보 수정
    //프론트에서 보내는 값
    /**
     *프론트에서 보내줄때 userid값을 파싱해서 보내줘야함  -> 기준값
     *
     this.email = email;
     this.password=password;
     this.username = username;
     this.phoneNumber = phoneNumber;
     */

    //body, header, status
    //유저정보 업데이트
    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
       public ResponseEntity<?> userUpdate(@RequestBody MemberDto memberDto){
        memberService.userUpdate(memberDto);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }





    //유저 프로필 사진 업데이트, 저장
    //파일 업로드시 @Requestaram으로 이용, @RequestBody는 에러 뱉음
    @PostMapping("/profileimage")
    public ResponseEntity<?> userImageUpdate(@RequestParam("file") MultipartFile file, @AuthUser UserPrincipal userPrincipal) throws Exception{
        //Member userId = memberRepository.findById(userPrincipal.getId()).orElseThrow(()-> new Exception("not found User imageUpdate"));
       Long userId = userPrincipal.getId();
       memberService.userImageUpdate(userId, file);
       return new ResponseEntity<>("ok", HttpStatus.OK);
    }



}
