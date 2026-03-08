package com.biraj.ecomerceappapi.services;


import com.biraj.ecomerceappapi.dto.LoginRequestDto;
import com.biraj.ecomerceappapi.dto.LoginResponseDto;
import com.biraj.ecomerceappapi.dto.SignUpResponseDto;
import com.biraj.ecomerceappapi.dto.UserSignUpDto;
import com.biraj.ecomerceappapi.entities.Role;
import com.biraj.ecomerceappapi.entities.User;
import com.biraj.ecomerceappapi.exceptions.AlreadyExists;
import com.biraj.ecomerceappapi.exceptions.InternalServerError;
import com.biraj.ecomerceappapi.exceptions.MissingFieldError;
import com.biraj.ecomerceappapi.repositories.UserRepository;
import com.biraj.ecomerceappapi.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private  final BCryptPasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private  final JWTUtil jwtUtil;


    public SignUpResponseDto signUp(UserSignUpDto userSignUpDto) {

        if(userSignUpDto.getName()==null || userSignUpDto.getName().trim().isEmpty()
        || userSignUpDto.getEmail()==null || userSignUpDto.getEmail().trim().isEmpty()
        || userSignUpDto.getPassword()==null || userSignUpDto.getPassword().isEmpty()){
            throw  new MissingFieldError("Please provide  details");
        }

        Optional<User> findUser = userRepository.findByEmail(userSignUpDto.getEmail());

        if(findUser.isPresent()){
            throw  new AlreadyExists("User with email " + userSignUpDto.getEmail() + " already exists");
        }
        User user = new User();
        user.setName(userSignUpDto.getName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        user.setRole(Role.valueOf(userSignUpDto.getRole()));
        User createdUser  = userRepository.save(user);

        return new SignUpResponseDto(createdUser.getId(),createdUser.getName(),createdUser.getEmail());
    }
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        if(loginRequestDto.getEmail()==null || loginRequestDto.getEmail().isEmpty()
                || loginRequestDto.getPassword()==null || loginRequestDto.getPassword().isEmpty()){
            throw new MissingFieldError("Please provide email and password");
        }

        Authentication authentication;

        try {

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword()
                    )
            );

        } catch (Exception e) {
            throw new InternalServerError("Invalid email or password");
        }

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new InternalServerError("Internal server error"));

        String token = jwtUtil.generateToken(user);

        return new LoginResponseDto(user.getId(), token, user.getEmail());
    }
}
