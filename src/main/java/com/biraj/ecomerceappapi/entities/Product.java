package com.biraj.ecomerceappapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String publisherId;

    @ElementCollection
    private List<Integer> sizes;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}