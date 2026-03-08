package com.biraj.ecomerceappapi.config;

import com.biraj.ecomerceappapi.entities.User;
import com.biraj.ecomerceappapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomUseDetailsService implements UserDetailsService {
    private final UserRepository repo;


    @Override
    public UserDetails loadUserByUsername(@NonNull String username)
            throws UsernameNotFoundException {
        User user = repo.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
        System.out.println(user.getEmail() + "Email");

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();
    }
}
