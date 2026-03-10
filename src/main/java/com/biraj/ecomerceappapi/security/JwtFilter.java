package com.biraj.ecomerceappapi.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.biraj.ecomerceappapi.config.CustomUseDetailsService;
import com.biraj.ecomerceappapi.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUseDetailsService customUseDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            System.out.println("Header: " + header);

            if (header != null && header.startsWith("Bearer")) {

                String token = header.substring(7);
                String email = jwtUtil.extractEmail(token);
                UserDetails userDetails = customUseDetailsService.loadUserByUsername(email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    System.out.println(userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            System.out.println("Error in JwtFilter:           " + ex);
            handlerExceptionResolver.resolveException(request, response, null, ex);

        }

    }
}
