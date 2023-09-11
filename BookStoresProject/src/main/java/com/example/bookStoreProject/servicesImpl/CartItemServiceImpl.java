package com.example.bookStoreProject.servicesImpl;


import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.entity.CartItem;
import com.example.bookStoreProject.entity.Customer;
import com.example.bookStoreProject.entity.Users;
import com.example.bookStoreProject.jwt.MyUserDetailsService;
import com.example.bookStoreProject.repository.BookRepository;
import com.example.bookStoreProject.repository.CartItemRepository;
import com.example.bookStoreProject.repository.CustomerRepository;
import com.example.bookStoreProject.services.CartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    

	private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
    
    @Autowired
    MyUserDetailsService myUserDetailsService;
    
    @Autowired
    CustomerRepository customerRepository; 
    
    @Autowired
    BookRepository bookRepository;
    
    

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
    
    @Override
	public List<CartItem> viewUserCart() {
		try {
			Users user = myUserDetailsService.getUserDetails();
			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
			return cartItemRepository.getCartByCustomerID(customer.getCustomerID());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<String> addToCart(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			Users user = myUserDetailsService.getUserDetails();
			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
			Book book = bookRepository.findById(Long.parseLong(map.get("bookID"))).get();
			
			
			CartItem cartItem = cartItemRepository.getCartByBookIdAndCustomerID(book.getBookID(), customer.getCustomerID());
			
			if(Objects.isNull(cartItem)) {
				cartItem = new CartItem();
				cartItem.setBook(bookRepository.findById(Long.parseLong(map.get("bookID"))).get());
				cartItem.setCustomer(customer);
				cartItem.setQuantity(1);
				cartItem.setRecordCreatedOn(new Timestamp(new Date().getTime()));
				cartItemRepository.save(cartItem);
			}else {
				
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItemRepository.save(cartItem);
			}
			return new ResponseEntity<String>("Item Added To Cart Successfully!!!", HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Something Went Wrong, Please Try Again After Sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}







