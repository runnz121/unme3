package com.esanghaesee.unme3.unme3.dto;

import com.esanghaesee.unme3.unme3.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String email;
    private String content;
    private Long parentId;

    //프론트에서 보내줄때 에초에 "post", "image"로 보내주면됨
    private String status;

}
