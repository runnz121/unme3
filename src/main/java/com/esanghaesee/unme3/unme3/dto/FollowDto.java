package com.esanghaesee.unme3.unme3.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class FollowDto {
    private Long id;
    private String name;
    private String email;
    private String profileImage;
//    private boolean followState;
//    private boolean equalUserState;

    @QueryProjection
    public FollowDto(Long id, String name, String email, String profileImage){
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }
}
