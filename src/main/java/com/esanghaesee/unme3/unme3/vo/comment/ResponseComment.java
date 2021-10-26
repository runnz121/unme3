package com.esanghaesee.unme3.unme3.vo.comment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ResponseComment {

    private String email;
    private String content;
    private Long parentId;

    //프론트에서 보내줄때 에초에 "post", "image"로 보내주면됨
    private String status;

}
