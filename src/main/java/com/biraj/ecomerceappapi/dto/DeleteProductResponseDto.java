package com.biraj.ecomerceappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProductResponseDto {
    private  Long   id;
    private  String message;
}
