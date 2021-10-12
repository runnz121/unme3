package com.esanghaesee.unme3.unme3.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    @QueryProjection
    public MemberDto(Long id, String email, String password, String name, String phoneNumber){
        this.id = id;
        this.email = email;
        this.password=password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
