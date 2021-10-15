package com.esanghaesee.unme3.unme3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name="follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_host_id")
    private Member memberHost;

    @ManyToOne
    @JoinColumn(name="member_follow_id")
    private Member memberFollow;

    @CreationTimestamp
    private LocalDateTime createTime;



}

