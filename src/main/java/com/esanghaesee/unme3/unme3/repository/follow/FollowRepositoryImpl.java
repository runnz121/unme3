package com.esanghaesee.unme3.unme3.repository.follow;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.QFollow;
import com.esanghaesee.unme3.unme3.domain.QMember;
import com.esanghaesee.unme3.unme3.dto.FollowDto;
import com.esanghaesee.unme3.unme3.dto.QFollowDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom{

//    private final EntityManager em;
//    private final JPAQueryFactory queryFactory;


    //follow와 member는 연관관계 맵핑이 불가능한 구조(mappedby 불가 )

    //내가 팔로잉 하고 있는 리스트를 불러오기
//    @Override
//    public List<FollowDto> getMyFollowingList(Long myId){
//        QMember member = QMember.member;
//        QFollow follow = QFollow.follow;
//
//        return queryFactory
//                .select(new QFollowDto(member.id, member.name, member.imageUrl))
//                .from(member)
//                .join()
//                .where()
//                .fetch();
//    }
}
