package com.esanghaesee.unme3.unme3.Security;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.enums.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;


public class UserPrincipal implements OAuth2User, UserDetails {

    private Long id;
    private String email;
    private String password;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //새로운 인증객체 생성 -> 아이디, 비밀번호 기반 로그인
    public static UserPrincipal create(Member member) {
        //권한을 새로 부여해주는 부분
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(MemberRole.USER.getKey()));

        return new UserPrincipal(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                authorities
        );
    }

    public static UserPrincipal create(Member member, Map<String, Object> attributes){
        //위에 메소드를 이용해서 유저인증생성
        UserPrincipal userPrincipal = UserPrincipal.create(member);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
    //principal 속성을 map 형태로 담기위해 지정
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    //오버라이딩 구현
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }
}
