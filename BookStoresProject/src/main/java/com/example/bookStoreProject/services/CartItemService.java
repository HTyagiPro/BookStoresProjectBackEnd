package com.example.bookStoreProject.services;

import com.example.bookStoreProject.entity.CartItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    Optional<CartItem> getCartItemById(Long id);

    CartItem saveCartItem(CartItem cartItem);

    void deleteCartItem(Long id);
    
    ResponseEntity<List<CartItem>> viewUserCart();
    
    public ResponseEntity<String> addToCart(Map<String, String> map);
}
