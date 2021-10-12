package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.domain.Post;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import com.esanghaesee.unme3.unme3.repository.post.PostRepository;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllposts(){
        List<Post>postList =  postRepository.loadAllPosts();
    }

}
