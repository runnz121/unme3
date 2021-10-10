package com.esanghaesee.unme3.unme3.domain.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MASTER("ROLE_MASTER");

    private String key;

    MemberRole(String key){
        this.key = key;
    }
}
