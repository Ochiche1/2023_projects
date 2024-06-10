package com.orbit.transaction.users.dto.response;

public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String username;
    private String password;
    private String message;
    private String refreshToken;

    public JwtResponse(String accessToken, String refreshToken, String username, String message) {
        this.accessToken = accessToken;
        this.username = username;
        this.message = message;
        this.refreshToken = refreshToken;
    }

    public JwtResponse() {

    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
