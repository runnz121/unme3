package com.esanghaesee.unme3.unme3.repository.image;

import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.dto.ImageDto;

import java.util.List;

public interface ImageRepositoryCustom {

    //userfeed사진 전체조회
    List<Image> getMyFeedImage(Long userId);



}
