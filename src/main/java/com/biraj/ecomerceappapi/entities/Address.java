package com.biraj.ecomerceappapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long HouseNo;
    private String street;
    private String city;   
    private String state;
    private String zipCode;
}
