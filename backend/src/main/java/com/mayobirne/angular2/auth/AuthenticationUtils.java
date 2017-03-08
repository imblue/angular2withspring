package com.mayobirne.angular2.auth;

import com.mayobirne.angular2.model.dto.AuthenticationTokenDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class AuthenticationUtils {

    private static final String KEY = "iamlegend";

    public static AuthenticationTokenDTO createAuthToken(UserDetails userDetails) {
        Long expireTime = System.currentTimeMillis() + 90 * 60 * 1000;

        String token = userDetails.getUsername() + ":" +
                expireTime + ":" +
                computeSignature(userDetails, expireTime);

        return new AuthenticationTokenDTO(token);
    }

    public static String getUserNameFromToken(AuthenticationTokenDTO authToken) {
        if (authToken == null || authToken.isEmpty()) {
            return null;
        }
        String[] parts = authToken.getToken().split(":");
        return parts[0];
    }

    public static boolean validateToken(AuthenticationTokenDTO authToken, UserDetails userDetails) {
        String[] parts = authToken.getToken().split(":");

        long expires;
        String signature;
        try {
            expires = Long.parseLong(parts[1]);
            signature = parts[2];
        } catch (Exception e) {
            return false;
        }

        if (expires < System.currentTimeMillis()) {
            return false;
        }

        return signature.equals(computeSignature(userDetails, expires));
    }

    private static String computeSignature(UserDetails userDetails, Long expireTime) {
        String signature = userDetails.getUsername() + ":" +
                expireTime + ":" +
                userDetails.getPassword() + ":" +
                KEY;

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signature.getBytes())));
    }

    static Set<GrantedAuthority> stringRolesToGrantedAuthorities(Set<String> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        // add minor roles
        if (roles.contains(Roles.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(Roles.ORGANISATOR));
            authorities.add(new SimpleGrantedAuthority(Roles.USER));
        }
        if (roles.contains(Roles.ORGANISATOR)) {
            authorities.add(new SimpleGrantedAuthority(Roles.USER));
        }

        return authorities;
    }
}
