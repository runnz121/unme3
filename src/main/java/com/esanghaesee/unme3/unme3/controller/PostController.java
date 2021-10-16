package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Post;
import com.esanghaesee.unme3.unme3.dto.PostDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import com.esanghaesee.unme3.unme3.repository.post.PostRepository;
import com.esanghaesee.unme3.unme3.service.PostService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

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

    @GetMapping("/allposts")
    public ResponseEntity<?> pagenationPost(@PageableDefault(page = 1, size = 15) Pageable pageable){
       return new ResponseEntity<>(postService.pagenationPost(pageable), HttpStatus.OK) ;
    }



//
//    @GetMapping("/all")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> getAllPosts(@AuthUser UserPrincipal userPrincipal){
//        List<Post> postList =  postRepository.loadAllPosts();
//        return new ResponseEntity<>(postList, HttpStatus.OK);
//    }
}
