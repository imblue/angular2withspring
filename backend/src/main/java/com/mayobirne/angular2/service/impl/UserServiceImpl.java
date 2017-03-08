package com.mayobirne.angular2.service.impl;

import com.mayobirne.angular2.model.converter.UserConverter;
import com.mayobirne.angular2.model.dto.UserDTO;
import com.mayobirne.angular2.model.entity.User;
import com.mayobirne.angular2.repository.UserRepository;
import com.mayobirne.angular2.service.AuthenticationService;
import com.mayobirne.angular2.service.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Christian Schuhmacher  on 27.04.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public UserDTO getCurrentUser() {
        Long id = authenticationService.getAuthenticatedUser();
        return findUserByID(id);
    }

    @Override
    public UserDTO findUserByID(Long id) {
        User entity = userRepository.findOne(id);
        return UserConverter.convertToDTO(entity);
    }

    @Override
    public UserDTO findUserByUsername(String username) throws UsernameNotFoundException {
        Validate.notEmpty(username, "Username must be set.");

        User entity = userRepository.findByUsername(username);
        if (entity == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return UserConverter.convertToDTO(entity);
    }
}
