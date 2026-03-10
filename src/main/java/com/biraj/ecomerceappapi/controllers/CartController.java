package com.biraj.ecomerceappapi.controllers;

import com.biraj.ecomerceappapi.dto.AddToCartRequestDto;
import com.biraj.ecomerceappapi.dto.AddToCartResponseDto;
import com.biraj.ecomerceappapi.entities.CartItem;
import com.biraj.ecomerceappapi.services.CartService;
import com.biraj.ecomerceappapi.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private  final JWTUtil jwtUtil;
    private  final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        List<CartItem> cartItems = cartService.fetchCartItemsForUser(userId);
        return ResponseEntity.ok(cartItems);
    }
    @PostMapping("/{id}")
    public ResponseEntity<AddToCartResponseDto> add(@PathVariable("id") Long id,@RequestBody AddToCartRequestDto body,
                                                    @RequestHeader("Authorization") String token){
        Long userId = jwtUtil.extractUserId(token.substring(7));
        AddToCartResponseDto response = cartService.addItem(userId,id,body);
        return ResponseEntity.ok(response);

    }
}
