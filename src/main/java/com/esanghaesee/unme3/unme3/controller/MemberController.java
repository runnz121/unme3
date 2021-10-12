package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;


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
    @PutMapping
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/update")
    public ResponseEntity<?> userUpdate(MemberDto memberDto){
        memberService.userUpdate(memberDto);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
