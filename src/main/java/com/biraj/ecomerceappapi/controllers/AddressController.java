package com.biraj.ecomerceappapi.controllers;

import com.biraj.ecomerceappapi.dto.AddressDto;
import com.biraj.ecomerceappapi.services.AddressService;
import com.biraj.ecomerceappapi.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private  final AddressService addressService;
    private  final JWTUtil jwtUtil;

    @PostMapping
    ResponseEntity<AddressDto> addAdress(@RequestBody AddressDto addressDto,
                                         @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDto,userId));
    }

    @PutMapping
    ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto,
                                            @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(addressService.addAddress(addressDto,userId));
    }

}
