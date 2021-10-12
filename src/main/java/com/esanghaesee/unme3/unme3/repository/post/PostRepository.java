package com.esanghaesee.unme3.unme3.repository.post;

import com.esanghaesee.unme3.unme3.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{


    //true 옵션 : 그대로 sql 쿼리 사용함
    @Query(value = "select * from post", nativeQuery = true)
    List<Post> loadAllPosts();
}
