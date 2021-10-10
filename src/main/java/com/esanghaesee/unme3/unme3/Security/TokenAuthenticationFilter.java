package com.esanghaesee.unme3.unme3.Security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenProvider tokenProvider;



//    public TokenAuthenticationFilter(TokenProvider tokenProvider){
//        this.tokenProvider = tokenProvider;
//    }
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    public TokenAuthenticationFilter(CustomUserDetailsService customUserDetailsService){
//        this.customUserDetailsService = customUserDetailsService;
//    }


    /*
        {
            AuthUser : Bearer +  token
        }
     */

    private String getJwtFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7, token.length());
        }
        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);

        //토큰 검증 후
        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
            Long userId = tokenProvider.getUserIdFromtToken(jwt);

            //userdetail 에 id 저장
            UserDetails userDetails = customUserDetailsService.loasUserById(userId);

            //authentication 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            //유효인증정보에 저장
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
