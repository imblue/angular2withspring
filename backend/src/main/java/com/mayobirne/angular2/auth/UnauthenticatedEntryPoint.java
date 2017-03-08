package com.mayobirne.angular2.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mayobirne.angular2.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
@Component("authenticationEntryPoint")
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), 1, 401, "Authentication is required to access this resource. TEST",
                "Authentication token was either missing or invalid.", authException);
        ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(responseEntity.getBody());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(json);
    }
}
