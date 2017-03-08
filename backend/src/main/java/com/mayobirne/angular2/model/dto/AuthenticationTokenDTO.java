package com.mayobirne.angular2.model.dto;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class AuthenticationTokenDTO {

    private String token;

    public AuthenticationTokenDTO() {}

    public AuthenticationTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isEmpty() {
        return token.isEmpty();
    }
}
