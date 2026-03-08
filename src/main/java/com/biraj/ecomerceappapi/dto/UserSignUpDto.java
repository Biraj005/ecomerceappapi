package com.biraj.ecomerceappapi.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignUpDto {
    private  String name;
    private  String email;
    private  String password;
    private  String role;
}
