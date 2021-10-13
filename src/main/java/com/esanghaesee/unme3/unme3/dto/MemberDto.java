package com.esanghaesee.unme3.unme3.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
public class MemberDto {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Long id;

    private String email;

    private String password;

    private String name;

    private String phonenumber;

    @QueryProjection
    public MemberDto(Long id, String email, String password, String name, String phonenumber){
        this.id = id;
        this.email = email;
        this.password=password;
        this.name = name;
        this.phonenumber = phonenumber;
    }
}
