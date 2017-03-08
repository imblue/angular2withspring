package com.mayobirne.angular2.service;

import com.mayobirne.angular2.model.dto.AuthenticationTokenDTO;
import com.mayobirne.angular2.model.dto.CredentialsDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
public interface AuthenticationService extends UserDetailsService {

    AuthenticationTokenDTO login(CredentialsDTO credentials);

    void authenticate(AuthenticationTokenDTO authToken, Object details);

    Long getAuthenticatedUser();
}
