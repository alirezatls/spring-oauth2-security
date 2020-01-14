package com.sample.oauth2service.domain;

public class UserDetails {
    private String token;
    private String username;
    private String newUsername;

    public UserDetails() {
    }

    public UserDetails(String token, String username, String newUsername) {
        this.token = token;
        this.username = username;
        this.newUsername = newUsername;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
}
