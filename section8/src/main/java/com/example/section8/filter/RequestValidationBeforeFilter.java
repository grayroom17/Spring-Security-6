package com.example.section8.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter {

    private static final Charset CREDENTIALS_CHARSET = StandardCharsets.UTF_8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String header = httpRequest.getHeader(AUTHORIZATION);
        if (header != null && !header.isEmpty()) {
            byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
            byte[] decoded;
            try {
                decoded = Base64.getDecoder().decode(base64Token);
                String token = new String(decoded, CREDENTIALS_CHARSET);
                int delim = token.indexOf(":");
                if (delim == -1) {
                    throw new BadCredentialsException("Invalid basic authentication token");
                }
                String email = token.substring(0, delim);
                if (email.toLowerCase().contains("test")) {
                    httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            } catch (IllegalArgumentException e) {
                throw new BadCredentialsException("Failed to decode basic authentication token");
            }
        }
        chain.doFilter(request, response);
    }

}
