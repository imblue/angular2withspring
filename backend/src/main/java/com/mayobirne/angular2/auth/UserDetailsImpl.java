package com.mayobirne.angular2.auth;

import com.mayobirne.angular2.model.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String encodedPassword;
    private Set<GrantedAuthority> authorities;
    private boolean enabled;

    public UserDetailsImpl(UserDTO user) {
        id = user.getId();
        username = user.getUsername();
        encodedPassword = user.getEncodedPassword();
        authorities = AuthenticationUtils.stringRolesToGrantedAuthorities(user.getRoles());
        enabled = user.isEnabled();
    }


    public Long getUserdId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return encodedPassword;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountNonExpired() {
        return enabled;
    }

    public boolean isAccountNonLocked() {
        return enabled;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }
}
