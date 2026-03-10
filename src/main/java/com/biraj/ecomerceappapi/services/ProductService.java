package com.biraj.ecomerceappapi.services;
import com.biraj.ecomerceappapi.dto.ProductAddRequsetDto;
import com.biraj.ecomerceappapi.entities.Category;
import com.biraj.ecomerceappapi.entities.Product;
import com.biraj.ecomerceappapi.exceptions.InternalServerError;
import com.biraj.ecomerceappapi.exceptions.ProductNotFoundException;
import com.biraj.ecomerceappapi.exceptions.UnauthorizedException;
import com.biraj.ecomerceappapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private  final ProductRepository productRepository;
    private final  CloudnaryImageService cloudnaryImageService;

    public   List<Product> getAllProducts(){

        return  productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }
    public Product  addProduct(ProductAddRequsetDto product, Long userId, MultipartFile file){

        if(product.getTitle()==null || product.getTitle().isEmpty()
        || product.getPrice()==null || product.getQuantity()==null || product.getQuantity()<=0
        || product.getSizes()==null || product.getSizes().isEmpty() || product.getCategory()==null){
            throw  new IllegalArgumentException("Please provide all details");
        }
        if(file==null || file.isEmpty()){
            throw  new IllegalArgumentException("Please provide product image");
        }

        final  Map uploadResult =  cloudnaryImageService.upload(file);

        if(uploadResult==null || uploadResult.get("url")==null){
            throw  new InternalServerError("Image upload failed");
        }

        Product newProduct =  new Product();
        newProduct.setCategory(Category.valueOf(product.getCategory()));
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setImageUrl((String) uploadResult.get("url"));
        newProduct.setSizes(product.getSizes());
        newProduct.setTitle(product.getTitle());
        newProduct.setPublisherId(userId);
        return productRepository.save(newProduct);
    }

    public void deleteProduct(Long id, Long userId) throws AccessDeniedException {
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));

        if(!product.getPublisherId().equals(userId)){
            throw  new UnauthorizedException("You are not authorized to delete this product");
        }
        productRepository.delete(product);
    }
}
