package com.biraj.ecomerceappapi.controllers;

import com.biraj.ecomerceappapi.entities.Product;
import com.biraj.ecomerceappapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private  final ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
//    @PostMapping
//    ResponseEntity<Product> addProduct(@RequestBody ){
//        Product product = productService.addProduct()
//    }

}
