package com.mayobirne.angular2.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mayobirne.angular2.error.ErrorMessage;
import com.mayobirne.angular2.model.dto.AuthenticationTokenDTO;
import com.mayobirne.angular2.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationTokenProcessingFilter")
@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    private static Logger LOG = LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);

    @Autowired
    private AuthenticationService authenticationService;


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = this.getAsHttpRequest(request);
        HttpServletResponse httpResponse = this.getAsHttpResponse(response);
        String authToken = this.extractAuthTokenFromRequest(httpRequest);
        Object details = new WebAuthenticationDetailsSource().buildDetails(httpRequest);

        if (authToken != null) {
            LOG.debug("AuthToken parsed: " + authToken);
            try {
                authenticationService.authenticate(new AuthenticationTokenDTO(authToken), details);
            } catch (AuthenticationException e) {
                ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), 1, 401, "You need to be authenticated to access this API.",
                        "No valid Authentication token set.", e);
                ResponseEntity<ErrorMessage> msg = new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(msg.getBody());
                httpResponse.setContentType("application/json");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getOutputStream().println(json);
                httpResponse.getOutputStream().close();
            }
        } else {
            LOG.debug("No AuthToken passed.");
        }

        chain.doFilter(request, response);
    }

    private HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }
        return (HttpServletRequest) request;
    }

    private HttpServletResponse getAsHttpResponse(ServletResponse response) {
        if (!(response instanceof HttpServletResponse)) {
            throw new RuntimeException("Expecting an HTTP response");
        }
        return (HttpServletResponse) response;
    }


    private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
        String authToken = httpRequest.getHeader("X-Auth-Token");

		/* If token not found get it from request parameter */
        if (authToken == null) {
            authToken = httpRequest.getParameter("token");
        }
        return authToken;
    }

}