package com.esanghaesee.unme3.unme3.service;

import com.esanghaesee.unme3.unme3.dto.FollowDto;
import com.esanghaesee.unme3.unme3.repository.follow.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;


//    //내가 following중엔 사람들을 리스트로 호출
//    public List<FollowDto> MyFollowingList (Long followingId, Long myId){
//        StringBuilder sb = new StringBuilder();
//        sb.append("select m.id, m.name, m.email, m.imageUrl, ");
//        sb.append("if(m.id = ?, true, false) equalUserState,");
//        sb.append("")
//    }


    @Transactional
    public void follow(Long followId, Long myId){
        followRepository.myFollow(followId, myId);
        System.out.println("내가 follow한 사람 : " + followId + "나의 아이디 : " + myId);
    }
}
