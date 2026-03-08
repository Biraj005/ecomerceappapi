package com.biraj.ecomerceappapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpResponseDto {
    private  Long id;
    private  String name;
    private  String email;
}
