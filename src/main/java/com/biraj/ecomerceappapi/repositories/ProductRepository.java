package com.biraj.ecomerceappapi.repositories;

import com.biraj.ecomerceappapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
