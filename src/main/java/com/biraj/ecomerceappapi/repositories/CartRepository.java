package com.biraj.ecomerceappapi.repositories;

import com.biraj.ecomerceappapi.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByUserId(Long userId);
}
