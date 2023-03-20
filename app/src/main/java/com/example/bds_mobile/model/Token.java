package com.example.bds_mobile.model;

public class Token {
    private String access_token;
    private String refresh_token;

    public Token(String accessToken, String refresh_token) {
        this.access_token = accessToken;
        this.refresh_token = refresh_token;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
