package com.biraj.ecomerceappapi.services;


import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.biraj.ecomerceappapi.dto.AddToCartRequestDto;
import com.biraj.ecomerceappapi.dto.AddToCartResponseDto;
import com.biraj.ecomerceappapi.dto.UpdateCartResponseDto;
import com.biraj.ecomerceappapi.entities.CartItem;
import com.biraj.ecomerceappapi.entities.Product;
import com.biraj.ecomerceappapi.entities.User;
import com.biraj.ecomerceappapi.exceptions.InternalServerError;
import com.biraj.ecomerceappapi.exceptions.MissingFieldError;
import com.biraj.ecomerceappapi.exceptions.ProductNotFoundException;
import com.biraj.ecomerceappapi.exceptions.UnauthorizedException;
import com.biraj.ecomerceappapi.repositories.CartRepository;
import com.biraj.ecomerceappapi.repositories.ProductRepository;
import com.biraj.ecomerceappapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private  final CartRepository cartRepository;
    private  final ProductRepository productRepository;
    private  final UserRepository userRepository;

    public List<CartItem> fetchCartItemsForUser(Long userId) {
        return  cartRepository.findByUserId(userId);
    }

    public AddToCartResponseDto addItem(Long userId, Long productId, AddToCartRequestDto body){
        if(productId==null){
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if(body.getSize()==null || body.getSize()<=0){
            throw new IllegalArgumentException("Please provide a valid size for the product");
        }

        Product findProduct = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Product not found with ID: "+productId));
        User user = userRepository.findById(userId).orElseThrow(()->new InternalServerError("Internal Server Error"));

        CartItem cartItem = new CartItem();
        cartItem.setSize(body.getSize());
        cartItem.setTitle(findProduct.getTitle());
        cartItem.setPrice(findProduct.getPrice());
        cartItem.setImageUrl(findProduct.getImageUrl());
        cartItem.setUser(user);
        cartItem.setPublisherId(findProduct.getPublisherId());
        cartItem.setQuantity(body.getQuantity());

        cartRepository.save(cartItem);

        AddToCartResponseDto response = new AddToCartResponseDto();
        response.setImageUrl(cartItem.getImageUrl());
        response.setPrice(cartItem.getPrice());
        response.setTitle(cartItem.getTitle());
        response.setSize(cartItem.getSize());
        response.setPublisherId(cartItem.getPublisherId());

        return response;
    }

    public UpdateCartResponseDto increaseCount(Long userId, Long id) {
        if(userId ==null || id==null){
            throw  new MissingFieldError("Please provide valid details ");
        }

        CartItem cartItem = cartRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException(id));

        if(!Objects.equals(cartItem.getUser().getId(), userId)){
            throw  new UnauthorizedException("You are not allowed to do the operation");
        }
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartRepository.save(cartItem);
        UpdateCartResponseDto updateCartResponseDto = new UpdateCartResponseDto();
        updateCartResponseDto.setId(cartItem.getId());
        updateCartResponseDto.setSize(cartItem.getSize());
        updateCartResponseDto.setQuantity(cartItem.getQuantity());
        updateCartResponseDto.setPrice(cartItem.getPrice());
        updateCartResponseDto.setTitle(cartItem.getTitle());
        updateCartResponseDto.setImageUrl(cartItem.getImageUrl());
        updateCartResponseDto.setPublisherId(cartItem.getPublisherId());
        return updateCartResponseDto;

    }

    public UpdateCartResponseDto decreaseCount(Long userId, Long id) {

        if(userId ==null || id==null){
            throw  new MissingFieldError("Please provide valid details ");
        }

        CartItem cartItem = cartRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException(id));

        if(!Objects.equals(cartItem.getUser().getId(), userId)){
            throw  new UnauthorizedException("You are not allowed to do the operation");
        }

        if(cartItem.getQuantity()==1){
            cartRepository.deleteById(id);
            UpdateCartResponseDto updateCartResponseDto = new UpdateCartResponseDto();
            updateCartResponseDto.setId(cartItem.getId());
            updateCartResponseDto.setSize(cartItem.getSize());
            updateCartResponseDto.setQuantity(cartItem.getQuantity()-1);
            updateCartResponseDto.setPrice(cartItem.getPrice());
            updateCartResponseDto.setTitle(cartItem.getTitle());
            updateCartResponseDto.setImageUrl(cartItem.getImageUrl());
            updateCartResponseDto.setPublisherId(cartItem.getPublisherId());
            return updateCartResponseDto;
        }
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        cartRepository.save(cartItem);
        UpdateCartResponseDto updateCartResponseDto = new UpdateCartResponseDto();
        updateCartResponseDto.setId(cartItem.getId());
        updateCartResponseDto.setSize(cartItem.getSize());
        updateCartResponseDto.setQuantity(cartItem.getQuantity());
        updateCartResponseDto.setPrice(cartItem.getPrice());
        updateCartResponseDto.setTitle(cartItem.getTitle());
        updateCartResponseDto.setImageUrl(cartItem.getImageUrl());
        updateCartResponseDto.setPublisherId(cartItem.getPublisherId());
        return updateCartResponseDto;
    }
    public void deleteItem(Long userId, Long id) {
        if(userId ==null || id==null){
            throw  new MissingFieldError("Please provide valid details ");
        }

        CartItem cartItem = cartRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException(id));

        if(!Objects.equals(cartItem.getUser().getId(), userId)){
            throw  new UnauthorizedException("You are not allowed to do the operation");
        }
        cartRepository.deleteById(id);
    }
}
