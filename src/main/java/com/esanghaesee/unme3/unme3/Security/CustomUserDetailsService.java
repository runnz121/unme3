package com.esanghaesee.unme3.unme3.Security;


import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

        return UserPrincipal.create(member);
    }

    @Transactional
    public UserDetails loasUserById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return UserPrincipal.create(member);
    }
}
