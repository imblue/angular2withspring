package com.mayobirne.angular2.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
public class CredentialsRest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


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
}
