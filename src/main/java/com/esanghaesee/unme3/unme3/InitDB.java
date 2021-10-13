package com.esanghaesee.unme3.unme3;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
        initService.dbInit5();
        initService.dbInit6();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1(){
            System.out.println("db1 injection");
            Member member = createMember("userA", "aaa","radfz1@naver.com");
            em.persist(member);
            Post posts = createPosts(member,"1번" );
            em.persist(posts);
        }
        public void dbInit2(){
            System.out.println("db2 injection");
            Member member = createMember("userB", "bbbbb","aanz2@naver.com");
            em.persist(member);
            Post posts = createPosts(member,"2번" );
            em.persist(posts);
        }
        public void dbInit3(){
            System.out.println("db3 injection");
            Member member = createMember("userC", "cccccc3","rbbbn3z@naver.com");
            em.persist(member);
            Post posts = createPosts(member,"3번" );
            em.persist(posts);
        }
        public void dbInit4(){
            System.out.println("db4 injection");
            Member member = createMember("userA", "aaa","radfz1@naver.com");
            em.persist(member);
            Post posts = createPosts(member,"1번" );
            em.persist(posts);
        }
        public void dbInit5(){
            System.out.println("db5 injection");
            Member member = createMember("userB", "bbbbb","aanz2@naver.com");
            em.persist(member);
            Post posts = createPosts(member,"2번" );
            em.persist(posts);
        }
        public void dbInit6(){
            System.out.println("db6 injection");
            Member member = createMember("111", "adsf","11@.com");
            em.persist(member);
            Post posts = createPosts(member,"3번" );
            em.persist(posts);
        }

        private Member createMember(String username, String password, String email){
            return Member.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .build();
        }

        private Post createPosts(Member member, String title){
            return Post.builder()
                    .member(member)
                    .title(title)
                    .build();
        }
    }
}
