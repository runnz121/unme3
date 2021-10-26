package com.esanghaesee.unme3.unme3.domain;

import com.esanghaesee.unme3.unme3.domain.date.CreateDate;
import com.esanghaesee.unme3.unme3.domain.date.UpdateDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends UpdateDate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member writer;

    private String content;

    private String email;

    //대댓글을 위한 조건
    //https://blog.naver.com/gmlwo308/222204404973
    //부모 댓글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Comment reparent;

    @OneToMany(mappedBy = "reparent", orphanRemoval = true)
    private List<Comment> childcomment = new ArrayList<>();

    private String status;

    @ManyToOne//내가 여러개 불러워 지는것
    @JoinColumn(name = "imageId")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;


    //댓글 생성
    public static Comment createComment(Member writer, String content, String status, Comment reparent) {
        Comment comment = new Comment();
        comment.writer = writer;
        comment.content = content;
        comment.status = status;
        comment.reparent = reparent;

        return comment;
    }
}
