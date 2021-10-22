package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.dto.ImageDto;
import com.esanghaesee.unme3.unme3.repository.image.ImageRepository;
import com.esanghaesee.unme3.unme3.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



@RestController
@RequestMapping("/image")
public class ImageController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Value("${file.path}")
    private String filepath;



    //@Requestbody -> http 요소(json)를 자바 요소로 바꿈
    //@ResponseBody -> 자바 객체를 http요소로 바꿔서 전송(json)
//    @GetMapping("/feed")
//    public ResponseEntity<?> getMyfeedImage(@AuthUser UserPrincipal userPrincipal){
//        Long userId = userPrincipal.getId();
//        List<Image> myFeedImages = imageService.getMyFeedImages(userId);
//        return new ResponseEntity<>(myFeedImages, HttpStatus.OK);
//    }


    //https://tecoble.techcourse.co.kr/post/2021-08-15-pageable/
    //예시 기대값 : GET /users?lastName=kim&page=3&size=10&sort=id,DESC

    //파라미터 값 기준으로 불러오기
    //https://aorica.tistory.com/154
    @GetMapping("/myfeed")
    public ResponseEntity<?> getMyfeedImage(@PageableDefault(page = 1, size = 15) Pageable pageable, @RequestParam("Id") UserPrincipal myId){
        Page<Image> result = imageService.getMyFeedImages(pageable, myId.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    //TEST 전체 사진 다 불러오기
//    @GetMapping("/allfeed")
//    public ResponseEntity<?> pagenationFeed(@PageableDefault(page = 1, size = 15) Pageable pageable){
//
//        return new ResponseEntity<>(imageService.pagenationFeed(pageable), HttpStatus.OK) ;
//    }

    //https://dev-gorany.tistory.com/17
    //http://localhost:8080/image/images?name=1.png
    //resourcefile [C:\myproject\unme3\src\main\resources\static\image\1.png]
    @GetMapping("/images")
    public ResponseEntity<Resource> display(@RequestParam("name") String filename){
        Resource resource = new FileSystemResource(filepath + filename);
        HttpHeaders header = new HttpHeaders();
        System.out.println("resource" + resource);

        try {
            Path filePath = Paths.get(filepath + filename);
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }


    //TEST 모든 사진 다불러오기
    //resource[file [C:\myproject\unme3\src\main\resources\static\image\71026a94-ce4e-490d-a5c1-fc0375c68420_121_2715745_1576166736873.jpg]]
    @GetMapping("/getAll")
    public ResponseEntity<?> displayAll(@AuthUser UserPrincipal myId) throws Exception{

        List<ImageDto> imageNameList = imageService.getAllFeed(myId.getId()); //filename이 담김

        List <Resource> resource = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();
        for(ImageDto filename : imageNameList){
            String getFilename = filename.getImageName();
            resource.add(new FileSystemResource(filepath + getFilename));
            System.out.println("resource" + resource);

            try {
                Path filePath = Paths.get(filepath + getFilename);
                header.add("Content-Type", Files.probeContentType(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }





    @PostMapping("/feed")
    public ResponseEntity<?> uploadMyFeedImage(MultipartFile[] files,  @AuthUser UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        imageService.uploadMyFeedImages(userId, files );
        System.out.println(Arrays.toString(files));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }



}
