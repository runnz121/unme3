package com.esanghaesee.unme3.unme3.repository.member;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.QMember;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.dto.QMemberDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityManager;
import java.util.UUID;


@Transactional
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;



//    public MemberRepositoryImpl(EntityManager em){
//        this.queryFactory = new JPAQueryFactory(em);
//    }


    //memberDto의 정보로 qmember를 업데이트 시키는 것
    //qmemger와 memberdto email이 같은 것을 기준으로 업데이트
    @Override
    public void updateMember(MemberDto memberDto){
       QMember qMember = QMember.member;
        JPAUpdateClause update = new JPAUpdateClause(em, qMember);
            update
                .set(qMember.email, memberDto.getEmail())
                .set(qMember.password, passwordEncoder.encode(memberDto.getPassword()))
                .set(qMember.name, memberDto.getName())
                .set(qMember.phonenumber, memberDto.getPhonenumber())
                .where(hasId(memberDto.getId()))
                .execute();
    }

    private BooleanExpression hasId(Long idCond){
        QMember qmember = QMember.member;
        return idCond == null ? qmember.id.isNull() : qmember.id.eq(idCond);
    }

    //회원 프로필 사진 업데이트, 로그인한 user id, 이미지 파일 받음
    @Override
    public void imageUpdate(Long userId, String fileName){
        QMember qMember = QMember.member;

        queryFactory.update(qMember)
                .set(qMember.imageUrl, fileName)
                .where(hasId(userId))
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
