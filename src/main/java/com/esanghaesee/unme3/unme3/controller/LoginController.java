package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.ApiResponse;
import com.esanghaesee.unme3.unme3.Security.TokenProvider;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.domain.enums.AuthProvider;
import com.esanghaesee.unme3.unme3.domain.enums.MemberRole;
import com.esanghaesee.unme3.unme3.dto.LoginDto;
import com.esanghaesee.unme3.unme3.dto.LoginResponseDto;
import com.esanghaesee.unme3.unme3.dto.SignupDto;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> loginController(@Valid @RequestBody LoginDto loginDto){

        Authentication loginUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(loginUser);
        String token = tokenProvider.createToken(loginUser);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }




    @PostMapping("/signup")
    public ResponseEntity<?> signupController(@Valid @RequestBody SignupDto signupDto) throws Exception {
        if (memberRepository.existsByEmail(signupDto.getEmail())){
            throw new Exception("Email address already exist");
        }

        Member member = new Member();
        member.setName(signupDto.getName());
        member.setEmail(signupDto.getEmail());
        member.setPassword(signupDto.getPassword());

        member.setAuthProvider(AuthProvider.local);
        member.setRole(MemberRole.USER);

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        Member results = memberRepository.save(member);

        URI toLocation = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(results.getId()).toUri();

        return ResponseEntity.created(toLocation)
                .body(new ApiResponse(true, "User registered successfully@"));

        //https://velog.io/@max9106/wtc-learning5
       // return ResponseEntity.created(URI.create("/")).build();
    }
}
