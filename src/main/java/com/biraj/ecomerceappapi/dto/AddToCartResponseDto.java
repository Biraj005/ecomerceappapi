package com.biraj.ecomerceappapi.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddToCartResponseDto {
    private String title;
    private String imageUrl;
    private Integer size;
    private BigDecimal price;
    private Long PublisherId;
}
