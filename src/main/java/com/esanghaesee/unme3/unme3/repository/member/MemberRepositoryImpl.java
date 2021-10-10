package com.esanghaesee.unme3.unme3.repository.member;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;


import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryImpl {

    private EntityManager em;

    private final JPAQueryFactory query;

    public MemberRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
