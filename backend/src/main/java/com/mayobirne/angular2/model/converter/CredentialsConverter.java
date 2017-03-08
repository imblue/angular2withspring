package com.mayobirne.angular2.model.converter;

import com.mayobirne.angular2.model.dto.CredentialsDTO;
import com.mayobirne.angular2.model.rest.CredentialsRest;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class CredentialsConverter {

    public static CredentialsDTO convertToDTO(CredentialsRest rest) {
        CredentialsDTO dto = new CredentialsDTO();
        dto.setUsername(rest.getUsername());
        dto.setPassword(rest.getPassword());
        return dto;
    }
}
