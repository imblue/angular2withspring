package com.mayobirne.angular2.model.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class UserDTO extends AbstractDTO<Long> {

    private String username;
    private String encodedPassword;
    private Set<String> roles;
    private Set<GrantedAuthority> authorities;
    private boolean enabled;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
