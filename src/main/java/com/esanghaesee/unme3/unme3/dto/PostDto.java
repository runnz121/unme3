package com.esanghaesee.unme3.unme3.dto;

import com.esanghaesee.unme3.unme3.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {

    private Long id;

    private String title;

    private String content;

    public PostDto(List<Post> postList) {
    }
}
