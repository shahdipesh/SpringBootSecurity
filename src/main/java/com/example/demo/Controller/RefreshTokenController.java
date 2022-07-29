package com.example.demo.Controller;

import com.example.demo.JwtUtil.GenerateTokenRequest;
import com.example.demo.JwtUtil.JwtResponse;
import com.example.demo.JwtUtil.JwtUtil;
import com.example.demo.JwtUtil.JwtRequest;
import com.example.demo.Security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class RefreshTokenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    GenerateTokenRequest generateTokenRequest;



    @PostMapping("/generate")
    public JwtResponse authenticate(@RequestBody GenerateTokenRequest request) {
        String username = jwtUtil.getUsernameFromToken(request.getRefreshToken());

            UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(username);

            if(userPrincipal == null){
                return null;
            }
            else{
                return new JwtResponse(jwtUtil.generateToken(userPrincipal), request.getRefreshToken());
            }


    }
}
