package com.esanghaesee.unme3.unme3.service;

import com.esanghaesee.unme3.unme3.dto.FollowDto;
import com.esanghaesee.unme3.unme3.repository.follow.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private EntityManager em;


    //내가 following중엔 사람들을 리스트로 호출
    public List<FollowDto> myFollowingList (Long myId){
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct m.id, m.name, m.email, m.profileImage ");
        sb.append("from follow f inner join member m on f.memberHostId = ? ");
        sb.append("and f.memberFollowId = m.id ");
        String str = sb.toString();
        Query query = em.createNativeQuery(str, "FollowDtoMapping")
                .setParameter(1, myId);
        List<FollowDto> followDtoList = query.getResultList();
        return followDtoList;
    }


    //내가 follower중엔 사람들을 리스트로 호출
    public List<FollowDto> myFollowerList (Long myId){
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct m.id, m.name, m.email, m.profileImage ");
        sb.append("from follow f inner join member m on f.memberHostId = m.id ");
        sb.append("and f.memberFollowId = ? ");
        String str = sb.toString();
        Query query = em.createNativeQuery(str, "FollowDtoMapping")
                .setParameter(1, myId);
        List<FollowDto> followDtoList = query.getResultList();
        return followDtoList;
    }



    @Transactional
    public void follow(Long followId, Long myId){
        followRepository.myFollow(followId, myId);
        System.out.println("내가 follow한 사람 : " + followId + "나의 아이디 : " + myId);
    }

    @Transactional
    public void unfollow(Long followId, Long myId){
        followRepository.myUnFollow(followId, myId);
        System.out.println("내가 unfollow한 사람 : " + followId + "나의 아이디 : " + myId);
    }
}
