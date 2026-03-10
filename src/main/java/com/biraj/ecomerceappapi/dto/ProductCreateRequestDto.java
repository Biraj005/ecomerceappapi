package com.biraj.ecomerceappapi.dto;

import java.math.BigDecimal;

import java.util.List;
import com.biraj.ecomerceappapi.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequestDto {
    private String title;

    private Long quantity;

    private BigDecimal price;
    private List<Integer> sizes;
    private Category category;

}
