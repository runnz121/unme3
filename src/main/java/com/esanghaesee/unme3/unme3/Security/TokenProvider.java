package com.esanghaesee.unme3.unme3.Security;


import com.esanghaesee.unme3.unme3.Security.config.SecProperties;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private SecProperties secProperties;

    public TokenProvider(SecProperties secProperties){
        this.secProperties = secProperties;
    }

    //토큰 생성
    public String createToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + secProperties.getAuth().getTokenExpiry());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secProperties.getAuth().getTokenSecret())
                .compact();

    }

    //토큰 파싱하여 id갖고오기
    public Long getUserIdFromtToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }


    //토큰 유효 검증
    //인자 토큰값이  secret으로 파싱한거와 가은 경우
    public boolean validateToken(String authToken) {
        try{
            Jwts.parser().setSigningKey(secProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (JwtException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
