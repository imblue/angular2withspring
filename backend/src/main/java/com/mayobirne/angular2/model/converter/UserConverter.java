package com.mayobirne.angular2.model.converter;

import com.mayobirne.angular2.model.dto.UserDTO;
import com.mayobirne.angular2.model.entity.User;
import com.mayobirne.angular2.model.rest.UserRest;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class UserConverter {

    // TODO

    public static UserDTO convertToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEncodedPassword(entity.getEncodedPassword());
        dto.setEnabled(entity.isEnabled());
        dto.setRoles(entity.getRoles());
        return dto;
    }

    public static UserRest convertToRest(UserDTO dto) {
        UserRest rest = new UserRest();
        rest.setId(dto.getId());
        rest.setUsername(dto.getUsername());
        rest.setEnabled(dto.isEnabled());
        return rest;
    }
}
