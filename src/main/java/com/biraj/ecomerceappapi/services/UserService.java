package com.biraj.ecomerceappapi.services;


import com.biraj.ecomerceappapi.dto.LoginRequestDto;
import com.biraj.ecomerceappapi.dto.LoginResponseDto;
import com.biraj.ecomerceappapi.dto.SignUpResponseDto;
import com.biraj.ecomerceappapi.dto.UserSignUpDto;
import com.biraj.ecomerceappapi.entities.User;
import com.biraj.ecomerceappapi.exceptions.AlreadyExists;
import com.biraj.ecomerceappapi.exceptions.MissingFieldError;
import com.biraj.ecomerceappapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private  final BCryptPasswordEncoder passwordEncoder;


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
        User createdUser  = userRepository.save(user);

        return new SignUpResponseDto(createdUser.getId(),createdUser.getName(),createdUser.getEmail());
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception {

        if(loginRequestDto.getEmail()==null || loginRequestDto.getEmail().isEmpty()
         || loginRequestDto.getPassword()==null || loginRequestDto.getPassword().isEmpty()){
            throw  new MissingFieldError("Please provide email and password");
        }

        throw  new Exception("Not implemented");


    }
}
