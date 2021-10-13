package com.esanghaesee.unme3.unme3.domain;

import com.esanghaesee.unme3.unme3.domain.enums.AuthProvider;
import com.esanghaesee.unme3.unme3.domain.enums.MemberRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    //프로필 이미지
    private String imageUrl;

    //추가

    private String phonenumber;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;


    //연관관계 주인은 mappedby옵션을 사용하지 않늗나 -> member는 연관관계 주인이 아니다 -> 이유는? 외래키를 갖고있지 않기 때문이다
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Save> save = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "member")
//    private List<Follow> follow = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Image> image = new ArrayList<>();

}
