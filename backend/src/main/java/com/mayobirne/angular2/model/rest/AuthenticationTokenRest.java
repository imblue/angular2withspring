package com.mayobirne.angular2.model.rest;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
public class AuthenticationTokenRest {

    private String token;

    public AuthenticationTokenRest() {}

    public AuthenticationTokenRest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
