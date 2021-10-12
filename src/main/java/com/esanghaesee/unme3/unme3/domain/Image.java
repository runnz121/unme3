package com.esanghaesee.unme3.unme3.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;




    @ManyToOne(fetch = FetchType.EAGER)//누르자마자 바로 호출
    @JoinColumn(name="member_id") //member클래스의 PK로 자동으로 조인된다. 컬럼을 지정해 주고 싶으면 referencedColumn을 사용하여 처리하면됨
    private Member member;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY) //위의 유저가 호출된다음에  쿼리 실행
    @JsonIgnore
    private List<Tag> tag;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Likes> likes;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comment;


}
