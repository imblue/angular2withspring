package com.mayobirne.angular2.service;

import com.mayobirne.angular2.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
public interface UserService {

    UserDTO getCurrentUser();

    UserDTO findUserByID(Long id);

    UserDTO findUserByUsername(String username) throws UsernameNotFoundException;

}
