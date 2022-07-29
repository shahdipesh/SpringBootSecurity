package com.example.demo.JwtUtil;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse {
    String token;
    String refreshToken;

    public JwtResponse(String token,String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
