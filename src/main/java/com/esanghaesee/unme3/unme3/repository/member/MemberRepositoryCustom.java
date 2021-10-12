package com.esanghaesee.unme3.unme3.repository.member;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;

import java.util.Optional;

public interface MemberRepositoryCustom {

    void updateMember(MemberDto memberDto);

}
