package com.esanghaesee.unme3.unme3.repository.follow;

import com.esanghaesee.unme3.unme3.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {



    //https://devhyogeon.tistory.com/4
    @Modifying
    @Query(value ="INSERT INTO follow(member_host_id, member_follow_id) VALUES(?1, ?2)", nativeQuery= true)
    void myFollow(Long followId, Long myId);


}
