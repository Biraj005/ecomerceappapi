package com.biraj.ecomerceappapi.services;

import com.biraj.ecomerceappapi.controllers.ProductController;
import com.biraj.ecomerceappapi.dto.ProductAddRequsetDto;
import com.biraj.ecomerceappapi.entities.Product;
import com.biraj.ecomerceappapi.exceptions.ProductNotFoundException;
import com.biraj.ecomerceappapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private  final ProductRepository productRepository;

    public   List<Product> getAllProducts(){
        return  productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }
    public Product addProduct(ProductAddRequsetDto product){
        throw  new IllegalArgumentException("Not implemented yet");
    }

}
