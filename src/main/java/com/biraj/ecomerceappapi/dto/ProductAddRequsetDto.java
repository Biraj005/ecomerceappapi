package com.biraj.ecomerceappapi.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAddRequsetDto {

    private String title;

    private String description;

    private String category;

    private Long quantity;

    private BigDecimal price;

    private String imageUrl;

    private List<Integer> sizes;

}