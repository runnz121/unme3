package com.esanghaesee.unme3.unme3.repository.member;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

        Optional<Member> findByEmail(String email);

        Boolean existsByEmail(String email);

//        void update(MemberDto memberDto);
}
