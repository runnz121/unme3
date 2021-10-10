package com.esanghaesee.unme3.unme3.domain;


import com.esanghaesee.unme3.unme3.domain.enums.AuthProvider;
import com.esanghaesee.unme3.unme3.domain.enums.MemberRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private String email;

    private String password;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private String imageUrl;
}
