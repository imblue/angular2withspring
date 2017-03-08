package com.mayobirne.angular2.model.converter;

import com.mayobirne.angular2.model.dto.AuthenticationTokenDTO;
import com.mayobirne.angular2.model.rest.AuthenticationTokenRest;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class AuthenticationTokenConverter {

    public static AuthenticationTokenRest convertToRest(AuthenticationTokenDTO dto) {
        return new AuthenticationTokenRest(dto.getToken());
    }
}
