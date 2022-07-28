package com.example.demo.Security;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Component
public class OauthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"message\":\"Successfully logged in\"}");
        OAuthUserPrincipal userPrincipal = new OAuthUserPrincipal((DefaultOidcUser) authentication.getPrincipal());
        String username = userPrincipal.getUsername();
        User user = userRepository.findByUsername(username);
        if(user == null) {
            user = new User();
            user.setUsername(username);
            user.setEmail(userPrincipal.getEmail());
            user.setPassword(alphaNumericString(10));
            user.setLoginMethod(1);
            user.setActive(1);
            user.setBlocked(0);
            user.setRoles("ROLE_USER");
            user.setPermissions("");
            userRepository.save(user);
        }
        else{
            userRepository.save(user);
        }
    }


    public static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
