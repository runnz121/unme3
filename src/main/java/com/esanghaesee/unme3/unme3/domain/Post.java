package com.esanghaesee.unme3.unme3.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="post")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post { //개인이 직접 작성한 글을 넣는 곳
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;


    //연관관계 -> 주인이다(member의 외래키를 갖고 있다.)
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
