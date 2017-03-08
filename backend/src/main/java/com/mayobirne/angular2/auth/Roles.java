package com.mayobirne.angular2.auth;

import java.util.*;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public interface Roles {

    String ADMIN = "ROLE_ADMIN";
    String ORGANISATOR = "ROLE_ORGANISATOR";
    String USER = "ROLE_USER";

    Set<String> ALL_ROLES = new HashSet<>(Arrays.asList(ADMIN, ORGANISATOR, USER));

}
