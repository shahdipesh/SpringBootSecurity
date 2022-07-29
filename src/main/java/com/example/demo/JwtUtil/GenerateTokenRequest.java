package com.example.demo.JwtUtil;

import org.springframework.stereotype.Component;

@Component
public class GenerateTokenRequest {
    private String refreshToken;

    public GenerateTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public GenerateTokenRequest() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
