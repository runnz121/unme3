package com.esanghaesee.unme3.unme3.Exception;

import lombok.Getter;

@Getter
public class NoUserException extends RuntimeException { //RunTimeException으로 받으면 method에 throw exception안받아도됨

    private String message;

    public NoUserException(){
        this.message = "유저를 찾을수 없습니다.";
    }
}
