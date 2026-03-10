package com.biraj.ecomerceappapi.controllers;

import java.util.List;

import com.biraj.ecomerceappapi.dto.ProductAddRequsetDto;
import com.biraj.ecomerceappapi.exceptions.InternalServerError;
import com.biraj.ecomerceappapi.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.biraj.ecomerceappapi.entities.Product;
import com.biraj.ecomerceappapi.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private  final ProductService productService;
    private  final  JWTUtil jwtUtil;

    @GetMapping
    ResponseEntity<List<Product>> getAllProduct(){
        System.out.println("Hellow");
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
   @PostMapping
   ResponseEntity<Product> addProduct(@ModelAttribute ProductAddRequsetDto product,
                                      @RequestParam("image")MultipartFile file
   , HttpServletRequest request) throws Exception {

       String authHeader = request.getHeader("Authorization");

       if(authHeader == null || !authHeader.startsWith("Bearer ")) {
           throw  new InternalServerError("Internal Server Error");
       }

       String token = authHeader.substring(7);

       Long userId = (jwtUtil.extractUserId(token));

       return  ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product,userId,file));



   }

}
