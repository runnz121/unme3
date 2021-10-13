package com.esanghaesee.unme3.unme3.service;

import com.esanghaesee.unme3.unme3.Exception.NoUserException;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class MemberService {



    @Autowired
    private MemberRepository memberRepository;


    @Value("${file.path}")
    private String uploadFolder;

    public void userUpdate(MemberDto updateValue)  {
        memberRepository.updateMember(updateValue);
    }


    //유저 피드 사진 저장
    public void userImageUpdate(Long userId, MultipartFile file) {

        UUID uuid = UUID.randomUUID();
        //이걸 전송
        String imageFilename = uuid + "_" + file.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFilename);

        try {
            Files.write(imageFilePath, file.getBytes());
        } catch (IOException ex){
            ex.printStackTrace();
        }

       memberRepository.imageUpdate(userId, imageFilename);
    }


}
