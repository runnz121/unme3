package com.esanghaesee.unme3.unme3.repository.member;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.QMember;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.dto.QMemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

//    public MemberRepositoryImpl(EntityManager em){
//        this.queryFactory = new JPAQueryFactory(em);
//    }


    //memberDto의 정보로 qmember를 업데이트 시키는 것
    //qmemger와 memberdto email이 같은 것을 기준으로 업데이트
    @Override
    public void updateMember(MemberDto memberDto){
       QMember qMember = QMember.member;
        queryFactory.update(qMember)
                .where(qMember.id.eq(memberDto.getId()))
                .set(qMember.email, memberDto.getEmail())
                .set(qMember.password, memberDto.getPassword())
                .set(qMember.name, memberDto.getName())
                .set(qMember.phoneNumber, memberDto.getPhoneNumber())
                .execute();
    }
}





//    private EntityManager em;
//
//    private final JPAQueryFactory query;
//
//    public MemberRepositoryImpl(EntityManager em) {
//        this.query = new JPAQueryFactory(em);
//    }
//
//    public void save(Member member) {
//        em.persist(member);
//    }
//
//    public List<Member> findAll(){
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//    }
