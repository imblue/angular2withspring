package com.mayobirne.angular2.service.impl;

import com.mayobirne.angular2.auth.AuthenticationUtils;
import com.mayobirne.angular2.auth.UserDetailsImpl;
import com.mayobirne.angular2.model.dto.AuthenticationTokenDTO;
import com.mayobirne.angular2.model.dto.CredentialsDTO;
import com.mayobirne.angular2.model.dto.UserDTO;
import com.mayobirne.angular2.service.AuthenticationService;
import com.mayobirne.angular2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public AuthenticationTokenDTO login(CredentialsDTO credentials) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

        Authentication authentication = authenticationManager.authenticate(token); // TODO Exceptions

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = loadUserByUsername(credentials.getUsername());
        return AuthenticationUtils.createAuthToken(userDetails);
    }

    @Override
    public void authenticate(AuthenticationTokenDTO authToken, Object details) {
        String userName = AuthenticationUtils.getUserNameFromToken(authToken);
        if (userName != null) {
            try {
                UserDetails userDetails = loadUserByUsername(userName);
                if (AuthenticationUtils.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(details);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new UsernameNotFoundException("Token " + authToken.getToken() + " is invalid.");
                }
            } catch (UsernameNotFoundException e) {
                throw new AuthenticationServiceException(e.getMessage());
            }
        }
    }

    @Override
    public Long getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetailsImpl) authentication.getPrincipal()).getUserdId();
    }

    // Implemented from UserDetailService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.findUserByUsername(username);
        return new UserDetailsImpl(user);
    }
}
