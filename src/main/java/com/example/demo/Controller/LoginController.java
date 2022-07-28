package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Security.UserPrincipal;
import com.example.demo.Service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/default")
    public UserPrincipal login(@RequestBody User user, HttpServletResponse response) throws IOException {


        try{
            Authentication token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication res = authenticationManager.authenticate(token);
        }
        catch(Exception e){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return null;
        }
        UserPrincipal userDetails = userPrincipalDetailsService.loadUserByUsername(user.getUsername());

        return userDetails;

    }

    @GetMapping("/oauth")
    public void oauth(HttpServletResponse httpResponse) throws IOException {
      //redirect to oauth2 provider
        httpResponse.sendRedirect("http://localhost:8080/oauth2/authorization/google");
    }
}
