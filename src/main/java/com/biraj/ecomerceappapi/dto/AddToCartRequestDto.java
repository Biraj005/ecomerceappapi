package com.biraj.ecomerceappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequestDto {
    private Integer size;
    private  Long quantity;
}