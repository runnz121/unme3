package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.dto.ImageDto;
import com.esanghaesee.unme3.unme3.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/image")
public class ImageController {


    @Autowired
    private ImageService imageService;

    //@Requestbody -> http 요소(json)를 자바 요소로 바꿈
    //@ResponseBody -> 자바 객체를 http요소로 바꿔서 전송(json)
    @GetMapping("/feed")
    public ResponseEntity<?> getMyfeedImage(@AuthUser UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        List<Image> myFeedImages = imageService.getMyFeedImages(userId);
        return new ResponseEntity<>(myFeedImages, HttpStatus.OK);
    }



    @PostMapping("/feed")
    public ResponseEntity<?> uploadMyFeedImage(MultipartFile[] files,  @AuthUser UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        imageService.uploadMyFeedImages(userId, files );
        System.out.println(Arrays.toString(files));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }



}
