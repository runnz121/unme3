package com.esanghaesee.unme3.unme3.service;

import com.esanghaesee.unme3.unme3.domain.Post;
import com.esanghaesee.unme3.unme3.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Page<Post> pagenationPost(Pageable pageable){

        return postRepository.findAll(pageable);
    }
}
