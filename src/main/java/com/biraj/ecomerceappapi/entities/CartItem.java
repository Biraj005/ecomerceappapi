package com.biraj.ecomerceappapi.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class CartItem {
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

    @Column(nullable = false)
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;
}
