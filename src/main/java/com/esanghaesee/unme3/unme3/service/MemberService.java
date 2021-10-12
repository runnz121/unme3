package com.esanghaesee.unme3.unme3.service;

import com.esanghaesee.unme3.unme3.Exception.NoUserException;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void userUpdate(MemberDto updateValue)  {
        memberRepository.updateMember(updateValue);
    }
}
