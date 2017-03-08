package com.mayobirne.angular2.controller;

import com.mayobirne.angular2.auth.Roles;
import com.mayobirne.angular2.model.converter.AuthenticationTokenConverter;
import com.mayobirne.angular2.model.converter.CredentialsConverter;
import com.mayobirne.angular2.model.converter.UserConverter;
import com.mayobirne.angular2.model.dto.AuthenticationTokenDTO;
import com.mayobirne.angular2.model.dto.UserDTO;
import com.mayobirne.angular2.model.rest.AuthenticationTokenRest;
import com.mayobirne.angular2.model.rest.CredentialsRest;
import com.mayobirne.angular2.model.rest.UserRest;
import com.mayobirne.angular2.service.AuthenticationService;
import com.mayobirne.angular2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
@Controller
@RequestMapping("/account")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<AuthenticationTokenRest> login(@RequestBody CredentialsRest credentials) {
        AuthenticationTokenDTO authToken = authenticationService.login(CredentialsConverter.convertToDTO(credentials));
        return new ResponseEntity<>(AuthenticationTokenConverter.convertToRest(authToken), HttpStatus.CREATED);
    }

    @Secured(value = Roles.USER)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserRest> getUser() {
        UserDTO dto = userService.getCurrentUser();
        UserRest rest = UserConverter.convertToRest(dto);
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }

}
