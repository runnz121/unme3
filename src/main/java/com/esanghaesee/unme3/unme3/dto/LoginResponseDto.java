package com.esanghaesee.unme3.unme3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginResponseDto {

    private String authToken ;
    private String tokenType = "Bearer";

    public LoginResponseDto(String authToken){
        this.authToken = authToken;
    }
}
