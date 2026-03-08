package com.biraj.ecomerceappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private  Long id;
    private String email;
    private  String  token;

}
