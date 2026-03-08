package com.biraj.ecomerceappapi.security;

import com.biraj.ecomerceappapi.config.CustomUseDetailsService;
import com.biraj.ecomerceappapi.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private  final JWTUtil jwtUtil;
    private  final CustomUseDetailsService customUseDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(header!=null && header.startsWith("Bearer")){

            String token = header.substring(7);
            String email = jwtUtil.extractEmail(token);
            UserDetails userDetails = customUseDetailsService.loadUserByUsername(email);

            if(email!=null  && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                      userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(auth);

            }



        }
            filterChain.doFilter(request,response);



    }
}
