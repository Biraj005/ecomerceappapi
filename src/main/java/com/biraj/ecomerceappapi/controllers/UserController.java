package com.biraj.ecomerceappapi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biraj.ecomerceappapi.dto.LoginRequestDto;
import com.biraj.ecomerceappapi.dto.LoginResponseDto;
import com.biraj.ecomerceappapi.dto.SignUpResponseDto;
import com.biraj.ecomerceappapi.dto.UserSignUpDto;
import com.biraj.ecomerceappapi.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    ResponseEntity<SignUpResponseDto> signUp(@RequestBody UserSignUpDto userSignUpDto){
        SignUpResponseDto signUpResponseDto = userService.signUp(userSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponseDto);
    }

    @PostMapping("login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        System.out.println("Hellow Contoller");
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

}
