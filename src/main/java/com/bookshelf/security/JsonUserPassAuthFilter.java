package com.bookshelf.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JsonUserPassAuthFilter extends AbstractAuthenticationProcessingFilter {

    ObjectMapper objectMapper = new ObjectMapper();

    String usernameParameter = "username";
    String passwordParameter = "password";

    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public JsonUserPassAuthFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        Map<String, Object> requestObject;
        try {
            requestObject = objectMapper.readValue(httpServletRequest.getInputStream(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            requestObject = new HashMap<>();
        }

        String username = Optional.ofNullable(requestObject.get(usernameParameter))
                .map(Object::toString)
                .orElse("");

        String password = Optional.ofNullable(requestObject.get(passwordParameter))
                .map(Object::toString)
                .orElse("");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
