package com.esanghaesee.unme3.unme3.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    private int id;
    private String username;
    private String name;
    private String profileImage;
    private boolean followState;
    private boolean equalUserState;
}
