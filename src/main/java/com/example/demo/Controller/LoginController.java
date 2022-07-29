package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.JwtUtil.JwtRequest;
import com.example.demo.JwtUtil.JwtResponse;
import com.example.demo.JwtUtil.JwtUtil;
import com.example.demo.Security.UserPrincipal;
import com.example.demo.Service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

//    @PostMapping("/default")
//    public UserPrincipal login(@RequestBody User user, HttpServletResponse response) throws IOException {
//
//
//        try{
//            Authentication token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            Authentication res = authenticationManager.authenticate(token);
//        }
//        catch(Exception e){
//           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//          return null;
//        }
//        UserPrincipal userDetails = userPrincipalDetailsService.loadUserByUsername(user.getUsername());
//
//        return userDetails;
//
//    }

    @PostMapping("/default")
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
        JwtResponse token = new JwtResponse(jwtUtil.generateToken(userDetails), jwtUtil.generateRefreshToken(userDetails));
        return token;
    }

    @GetMapping("/oauth")
    public void oauth(HttpServletResponse httpResponse) throws IOException {
      //redirect to oauth2 provider
        httpResponse.sendRedirect("http://localhost:8080/oauth2/authorization/google");
    }
}
