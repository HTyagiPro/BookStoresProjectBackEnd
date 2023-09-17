package com.example.bookStoreProject.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStoreProject.entity.CartItem;
import com.example.bookStoreProject.services.CartItemService;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService  cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("")
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public Optional<CartItem> getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @PostMapping("/")
    public CartItem saveCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }
    
    @GetMapping("/viewUserCart")
    public  ResponseEntity<List<CartItem>>  viewUserCart(){
    	return cartItemService.viewUserCart();
    }
    
    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody(required = true) Map<String, String> map){
		try {
			return cartItemService.addToCart(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Something Went Wrong, Try again after Sometime!!", HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
    
   
    
}
