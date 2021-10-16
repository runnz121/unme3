package com.esanghaesee.unme3.unme3.controller;

import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private MemberRepository memberRepository;

    //유저 아이디를 반환하는 곳!
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentUser(@AuthUser UserPrincipal userPrincipal) throws Exception {
        return memberRepository.findById(userPrincipal.getId())
                .orElseThrow(()-> new Exception("not found"));
    }


//    @GetMapping("/user/search")
//    public Member searchUser(@RequestBody MemberDto memberDto) throws Exception{
//        return memberRepository.findById(memberDto.getId())
//                .orElseThrow(() -> new Exception("Cannot find user"));
//    }
}
