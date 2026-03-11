package com.biraj.ecomerceappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartResponseDto {

    private Long id;

    private String title;

    private BigDecimal price;

    private String imageUrl;


    private Long publisherId;

    private Integer size;


    private  Long quantity;

}
