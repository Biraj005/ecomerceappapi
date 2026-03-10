package com.biraj.ecomerceappapi.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.biraj.ecomerceappapi.dto.DeleteProductResponseDto;
import com.biraj.ecomerceappapi.dto.ProductAddRequsetDto;
import com.biraj.ecomerceappapi.exceptions.InternalServerError;
import com.biraj.ecomerceappapi.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   ResponseEntity<Product> addProduct(@ModelAttribute("product") ProductAddRequsetDto product,
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
   @DeleteMapping("/{id}")
    ResponseEntity<DeleteProductResponseDto> deleteProduct(@PathVariable Long id,@RequestHeader("Authorization") String token) throws AccessDeniedException {

         Long userId = jwtUtil.extractUserId(token.substring(7));
         productService.deleteProduct(id,userId);
         return ResponseEntity.ok(new DeleteProductResponseDto(id,"Product with id " + id + " deleted successfully"));
   }

}
