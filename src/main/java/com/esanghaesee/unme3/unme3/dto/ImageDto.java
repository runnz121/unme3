package com.esanghaesee.unme3.unme3.dto;


import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private String caption;

    private String location;

    private String tags;

    public Image toEntity(String imageUrl, Member member){
        return Image.builder()
                .location(location)
                .caption(caption)
                .imageUrl(imageUrl)
                .member(member)
                .build();
    }
}
