package com.bookstore.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.bookstore.entity.CartItem;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    Optional<CartItem> getCartItemById(Long id);

    CartItem saveCartItem(CartItem cartItem);

    void deleteCartItem(Long id);
    
    ResponseEntity<List<CartItem>> viewUserCart();
    
    public ResponseEntity<String> addToCart(Map<String, String> map);
    
    public ResponseEntity<String> deleteFromCart(Map<String, String> map);
}
