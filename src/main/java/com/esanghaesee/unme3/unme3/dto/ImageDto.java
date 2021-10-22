package com.esanghaesee.unme3.unme3.dto;


import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDto {

    private String imageName;

    private String caption;

    private String location;

    private String tags;

    public ImageDto(String imageName){
        this.imageName = imageName;
    }


    public Image toEntity(String imageUrl, Member member){
        return Image.builder()
                .location(location)
                .caption(caption)
                .imageUrl(imageUrl)
                .member(member)
                .build();
    }
}
