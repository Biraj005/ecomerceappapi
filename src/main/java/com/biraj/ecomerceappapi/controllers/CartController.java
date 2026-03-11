package com.biraj.ecomerceappapi.controllers;

import com.biraj.ecomerceappapi.dto.AddToCartRequestDto;
import com.biraj.ecomerceappapi.dto.AddToCartResponseDto;
import com.biraj.ecomerceappapi.dto.UpdateCartResponseDto;
import com.biraj.ecomerceappapi.entities.CartItem;
import com.biraj.ecomerceappapi.services.CartService;
import com.biraj.ecomerceappapi.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.biraj.ecomerceappapi.dto.DeletItemDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final JWTUtil jwtUtil;
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        List<CartItem> cartItems = cartService.fetchCartItemsForUser(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AddToCartResponseDto> add(@PathVariable("id") Long id, @RequestBody AddToCartRequestDto body,
            @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        AddToCartResponseDto response = cartService.addItem(userId, id, body);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/increase/{id}")
    public ResponseEntity<UpdateCartResponseDto> IncreaseCount(@PathVariable("id") Long id,
            @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        UpdateCartResponseDto response = cartService.increaseCount(userId, id);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/decrease/{id}")
    public ResponseEntity<UpdateCartResponseDto> decreaseCount(@PathVariable("id") Long id,
            @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        UpdateCartResponseDto response = cartService.decreaseCount(userId, id);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletItemDto> delete(@PathVariable("id") Long id,
            @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        cartService.deleteItem(userId, id);
        DeletItemDto response = new DeletItemDto();
        response.setItemId(id);
        response.setUserId(userId);
        response.setMessage("Item removed from cart successfully");
        return ResponseEntity.ok(response);
    }

}
