package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Post;
import com.esanghaesee.unme3.unme3.dto.PostDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import com.esanghaesee.unme3.unme3.repository.post.PostRepository;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/all")

    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList =  postRepository.loadAllPosts();

        if(postList == null){
            System.out.println("====================null===============");
        }
        System.out.println("======================here=================");
        System.out.println(postList);
       return new ResponseEntity<>(postList, HttpStatus.OK);
    }


//
//    @GetMapping("/all")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> getAllPosts(@AuthUser UserPrincipal userPrincipal){
//        List<Post> postList =  postRepository.loadAllPosts();
//        return new ResponseEntity<>(postList, HttpStatus.OK);
//    }
}
