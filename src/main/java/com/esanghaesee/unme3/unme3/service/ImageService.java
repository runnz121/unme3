package com.esanghaesee.unme3.unme3.service;

import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.Tag;
import com.esanghaesee.unme3.unme3.dto.ImageDto;
import com.esanghaesee.unme3.unme3.repository.image.ImageRepository;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import com.esanghaesee.unme3.unme3.repository.tag.TagRepository;
import com.esanghaesee.unme3.unme3.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.event.FocusAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TagRepository tagRepository;

    @Value("${file.path}")
    private String uploadFolder;


    //userid조회해서 전체 feed 사진 갖고오기
    public List<Image> getMyFeedImages(Long userId){
        return imageRepository.getMyFeedImage(userId);
    }


    //s3에 이미지만 저장하는 방법
    //https://blog.theodo.com/2019/07/aws-s3-upload-django/
    //파일 저장
    //userid 기준으로 feed 사진 업로드하기
    public void uploadMyFeedImages(Long userId, MultipartFile[] files){

        UUID uuid = UUID.randomUUID();
        Member member = memberRepository.findById(userId).orElseThrow(null);

        //파일 위치 저장
        for(MultipartFile FilesTofile: files){
            String fileurl = uuid + "_" + FilesTofile.getOriginalFilename()+  "_" +  userId;
            //파일을 갖고올 곳을 지정  https://codevang.tistory.com/158
            Path imageFilepath = Paths.get(uploadFolder + fileurl);
            //https://stackoverflow.com/questions/4350084/byte-to-file-in-java
            try {
                //yml 파일에 지정한 부분
                //여기서 C://에 이미 파일을 저장 해놨음
                Files.write(imageFilepath, FilesTofile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //이미지저장
            Image image = new Image();
                image.setImageUrl(fileurl);
                image.setMember(member);
            Image imageForSave = imageRepository.save(image);

//            //태그저장
//            List<String> tagNames = Utils.tagParse(image.getTag());
//            for (String name : tagNames) {
//                Tag tag = Tag.builder()
//                        .image(imageForSave)
//                        .name(name)
//                        .build();
//                tagRepository.save(tag);
//            }
        }
    }
}
