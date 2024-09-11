package com.example.stock.infrastructure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    private static final String MESSAGE_EXCEPTION = "Authorization header is missing or malformed";
    private static final String MESSAGE_AUTHORIZATION = "Authorization";
    private static final String MESSAGE_DENIED = "Access denied for non-admin users";
    private static final String MESSAGE_INVALID = "Invalid JWT token";

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isOptionsRequest(request)) {
            handleOptionsRequest(response, filterChain, request);
            return;
        }

        String authHeader = request.getHeader(MESSAGE_AUTHORIZATION);
        if (!isValidAuthHeader(authHeader)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MESSAGE_EXCEPTION);
            return;
        }

        String token = authHeader.substring(7);
        try {
            Claims claims = parseToken(token);
            String role = claims.get("role", String.class);

            if (!isAuthorizedAccess(request, role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN,MESSAGE_DENIED );
                return;
            }

            request.setAttribute("claims", claims);
            request.setAttribute("stock", servletRequest.getParameter("id"));

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,MESSAGE_INVALID);
        }
    }

    private boolean isOptionsRequest(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod());
    }

    private void handleOptionsRequest(HttpServletResponse response, FilterChain filterChain, HttpServletRequest request) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        filterChain.doFilter(request, response);
    }

    private boolean isValidAuthHeader(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private Claims parseToken(String token) {
        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    private boolean isAuthorizedAccess(HttpServletRequest request, String role) {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/admin")) {
            return "A".equals(role);
        }
        if (requestURI.startsWith("/shared")) {
            return "A".equals(role) || "T".equals(role);
        }
        return true;
    }
}
