package com.esanghaesee.unme3.unme3.repository.follow;

import com.esanghaesee.unme3.unme3.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {



    //https://devhyogeon.tistory.com/4
    @Modifying
    @Query(value ="INSERT INTO follow(memberHostId, memberFollowId) VALUES(?2, ?1)", nativeQuery= true)
    void myFollow(Long followId, Long myId);

    @Modifying
    @Query(value ="DELETE FROM follow WHERE memberHostId = ?2 AND memberFollowId = ?1", nativeQuery= true)
    void myUnFollow(Long followId, Long myId);



}
