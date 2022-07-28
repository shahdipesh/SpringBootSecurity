package com.example.demo.Controller;

import com.example.demo.JwtUtil.JwtResponse;
import com.example.demo.JwtUtil.JwtUtil;
import com.example.demo.JwtUtil.JwtRequest;
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
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;



    @PostMapping
    public JwtResponse authenticate(@RequestBody JwtRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        try{
            Authentication token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication res = authenticationManager.authenticate(token);
        }
        catch(Exception e){
           throw new RuntimeException("Invalid username or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        JwtResponse token = new JwtResponse(jwtUtil.generateToken(userDetails));
        return token;

    }
}
