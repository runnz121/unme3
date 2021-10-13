package com.esanghaesee.unme3.unme3.repository.member;

import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.MemberDto;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;

public interface MemberRepositoryCustom {

    void updateMember(MemberDto memberDto);

    //유저 프로필 이미지 사진 업데이트
    void imageUpdate(Long userId, String imageFilename);


}
