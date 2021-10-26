package com.esanghaesee.unme3.unme3.repository.comment;

import com.esanghaesee.unme3.unme3.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom{

}
