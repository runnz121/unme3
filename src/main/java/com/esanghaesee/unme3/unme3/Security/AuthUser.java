package com.esanghaesee.unme3.unme3.Security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal//userprincipal에서 return 한 객체를 받아옴
public @interface AuthUser {

}
