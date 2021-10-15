package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.FollowDto;
import com.esanghaesee.unme3.unme3.repository.follow.FollowRepository;
import com.esanghaesee.unme3.unme3.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private FollowRepository followRepository;

    //내가 following중인 사람들을 불러옴
//    @GetMapping("/followingList")
//    public ResponseEntity<?> getMyFollowingList(@PathVariable Long followingUserId, @AuthUser UserPrincipal userPrincipal){
//        List<FollowDto> followingList = followService.MyFollowingList(followingUserId, userPrincipal.getId());
//        return new ResponseEntity<>(followingList, HttpStatus.OK);
//    }

    //내가 팔로우할 id를 선택하면 이쪽으로 옴
    @PostMapping("/follow/{toFollowId}")
    public ResponseEntity<?> follow(@PathVariable Long toFollowId, @AuthUser UserPrincipal myId){
        followService.follow(toFollowId, myId.getId());
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
}
